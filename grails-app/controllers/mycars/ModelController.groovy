package mycars

import org.springframework.dao.DataIntegrityViolationException

class ModelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [modelInstanceList: Model.list(params), modelInstanceTotal: Model.count()]
    }

    def create() {
        [modelInstance: new Model(params)]
    }

    def save() {
        def modelInstance = new Model(params)
        if (!modelInstance.save(flush: true)) {
            render(view: "create", model: [modelInstance: modelInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'model.label', default: 'Model'), modelInstance.id])
        redirect(action: "show", id: modelInstance.id)
    }

    def show() {
        def modelInstance = Model.get(params.id)
        if (!modelInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "list")
            return
        }

        [modelInstance: modelInstance]
    }

    def edit() {
        def modelInstance = Model.get(params.id)
        if (!modelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "list")
            return
        }

        [modelInstance: modelInstance]
    }

    def update() {
        def modelInstance = Model.get(params.id)
        if (!modelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (modelInstance.version > version) {
                modelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'model.label', default: 'Model')] as Object[],
                          "Another user has updated this Model while you were editing")
                render(view: "edit", model: [modelInstance: modelInstance])
                return
            }
        }

        modelInstance.properties = params

        if (!modelInstance.save(flush: true)) {
            render(view: "edit", model: [modelInstance: modelInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'model.label', default: 'Model'), modelInstance.id])
        redirect(action: "show", id: modelInstance.id)
    }

    def delete() {
        def modelInstance = Model.get(params.id)
        if (!modelInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "list")
            return
        }

        try {
            modelInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'model.label', default: 'Model'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
