package mycars

import org.springframework.dao.DataIntegrityViolationException

class MakeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [makeInstanceList: Make.list(params), makeInstanceTotal: Make.count()]
    }

    def create() {
        [makeInstance: new Make(params)]
    }

    def save() {
        def makeInstance = new Make(params)
        if (!makeInstance.save(flush: true)) {
            render(view: "create", model: [makeInstance: makeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'make.label', default: 'Make'), makeInstance.id])
        redirect(action: "show", id: makeInstance.id)
    }

    def show() {
        def makeInstance = Make.get(params.id)
        if (!makeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "list")
            return
        }

        [makeInstance: makeInstance]
    }

    def edit() {
        def makeInstance = Make.get(params.id)
        if (!makeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "list")
            return
        }

        [makeInstance: makeInstance]
    }

    def update() {
        def makeInstance = Make.get(params.id)
        if (!makeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (makeInstance.version > version) {
                makeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'make.label', default: 'Make')] as Object[],
                          "Another user has updated this Make while you were editing")
                render(view: "edit", model: [makeInstance: makeInstance])
                return
            }
        }

        makeInstance.properties = params

        if (!makeInstance.save(flush: true)) {
            render(view: "edit", model: [makeInstance: makeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'make.label', default: 'Make'), makeInstance.id])
        redirect(action: "show", id: makeInstance.id)
    }

    def delete() {
        def makeInstance = Make.get(params.id)
        if (!makeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "list")
            return
        }

        try {
            makeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'make.label', default: 'Make'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
