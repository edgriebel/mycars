package mycars



import org.junit.*
import grails.test.mixin.*

@TestFor(MakeController)
@Mock(Make)
class MakeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/make/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.makeInstanceList.size() == 0
        assert model.makeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.makeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.makeInstance != null
        assert view == '/make/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/make/show/1'
        assert controller.flash.message != null
        assert Make.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/make/list'


        populateValidParams(params)
        def make = new Make(params)

        assert make.save() != null

        params.id = make.id

        def model = controller.show()

        assert model.makeInstance == make
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/make/list'


        populateValidParams(params)
        def make = new Make(params)

        assert make.save() != null

        params.id = make.id

        def model = controller.edit()

        assert model.makeInstance == make
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/make/list'

        response.reset()


        populateValidParams(params)
        def make = new Make(params)

        assert make.save() != null

        // test invalid parameters in update
        params.id = make.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/make/edit"
        assert model.makeInstance != null

        make.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/make/show/$make.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        make.clearErrors()

        populateValidParams(params)
        params.id = make.id
        params.version = -1
        controller.update()

        assert view == "/make/edit"
        assert model.makeInstance != null
        assert model.makeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/make/list'

        response.reset()

        populateValidParams(params)
        def make = new Make(params)

        assert make.save() != null
        assert Make.count() == 1

        params.id = make.id

        controller.delete()

        assert Make.count() == 0
        assert Make.get(make.id) == null
        assert response.redirectedUrl == '/make/list'
    }
}
