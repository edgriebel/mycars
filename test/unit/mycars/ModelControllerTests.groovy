package mycars



import org.junit.*
import grails.test.mixin.*

@TestFor(ModelController)
@Mock(Model)
class ModelControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/model/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.modelInstanceList.size() == 0
        assert model.modelInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.modelInstance != null
    }

    void testSave() {
        controller.save()

        assert model.modelInstance != null
        assert view == '/model/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/model/show/1'
        assert controller.flash.message != null
        assert Model.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/model/list'


        populateValidParams(params)
        def model = new Model(params)

        assert model.save() != null

        params.id = model.id

        def model = controller.show()

        assert model.modelInstance == model
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/model/list'


        populateValidParams(params)
        def model = new Model(params)

        assert model.save() != null

        params.id = model.id

        def model = controller.edit()

        assert model.modelInstance == model
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/model/list'

        response.reset()


        populateValidParams(params)
        def model = new Model(params)

        assert model.save() != null

        // test invalid parameters in update
        params.id = model.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/model/edit"
        assert model.modelInstance != null

        model.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/model/show/$model.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        model.clearErrors()

        populateValidParams(params)
        params.id = model.id
        params.version = -1
        controller.update()

        assert view == "/model/edit"
        assert model.modelInstance != null
        assert model.modelInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/model/list'

        response.reset()

        populateValidParams(params)
        def model = new Model(params)

        assert model.save() != null
        assert Model.count() == 1

        params.id = model.id

        controller.delete()

        assert Model.count() == 0
        assert Model.get(model.id) == null
        assert response.redirectedUrl == '/model/list'
    }
}
