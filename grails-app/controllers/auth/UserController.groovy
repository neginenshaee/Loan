package auth

import commands.UserCommand
import exceptions.UserNotFoundException
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import loan.LoanRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN','ROLE_USER'])
class UserController {

    def userService

    def index() {

    }

    def show(Long id) {
        User user = findUser(id)
        respond user
    }

    private User findUser(long id) {
        User user = userService.get(id)
        if (user == null) {
            log.info(message(code: 'user.not.found.message'))
            flash.message = (message(code: 'user.not.found.message', status: NOT_FOUND))
        } else {
            log.info(user.toString())
        }
        user
    }

    def create() {
        respond new User(params)
    }

    def save(UserCommand command) {
        if(command.validate(['firstName','lastName','username','password','email','country','address'])) {
            User user = userService.save(command)
            log.info(message(code: 'user.save.success.message'))
            flash.message = (message(code: 'user.save.success.message', status: OK))
            redirect user
        }else{
            log.info(command.errors.toString())
            flash.message = command.errors
            render (view: 'create', model: [params: params])
            return
        }
    }

    def confirm(String token){
        User user = userService.confirmUser(token)
        if(user == null){
            log.info(message(code: 'token.not.found.message'))
            flash.message = (message(code: 'token.not.found.message', status: NOT_FOUND))
            render(view: '/login/auth')
        }else {
            log.info(message(code: 'user.confirm.message'))
            flash.message = (message(code: 'user.confirm.message', status: OK))
            redirect(action: 'show', id: user.id)
        }
    }

    def edit(Long id) {
        User user = userService.get(id)

        params.id = user.getId()
        params.firstName = user.getFirstName()
        params.lastName = user.getLastName()
        params.address = user.getAddress()
        params.country = user.getCountry()
        render(view: '/user/edit', model: [params: params])
    }

    def password(Long id) {
        User user = userService.get(id)
        params.id = user.getId()
        render(view: '/user/password', model: [params: params])
    }

    def changePassword(UserCommand command) {
        if (command.validate(["oldPassword","password","repeatPassword"])) {
            userService.updatePassword(command)
        }else{
            flash.message = command.errors
            render (view: 'password', model: [params: params])
            return
        }
        redirect(controller: "logout")
    }

    def update(UserCommand command) {
        User user
        if(command.validate(["firstName", "lastName","country", "address","image"])) {
            try {
                command.image = request.getFile('userImage').getBytes()
                user = userService.update(command)
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user])
                        redirect user
                    }
                '*'{ respond user, [status: OK] }
        }
            } catch (ValidationException e) {
                respond command.errors, view: 'edit'
                return
            }
        }else{
            flash.message = command.errors
            render (view: 'edit', model: [params: params])
            return
        }

    }

    def getImage(Long id) {
        def user = User.get(id)
        if (user != null) {
            response.contentType = user.imageContentType == null ? "image/jpeg" : user.imageContentType
            response.contentLength = user.image == null ? 0 : user.image.size()
            response.outputStream << user.image
        } else {
            byte [] temp = [-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 2, 0, 0, 0, 2, 0, 8, 3, 0, 0, 0, -61, -90, 36, -56, 0, 0, 0, 3, 115, 66, 73, 84, 8, 8, 8, -37, -31, 79, -32, 0, 0, 0, 21, 80, 76, 84, 69, 71, 112, 76, 125, 122, 122, 126, 124, 124, 126, 125, 125, 125, 125, 125, 126, 126, 126, 126, 125, 125, -9, 109, -14, -58, 0, 0, 0, 6, 116, 82, 78, 83, 0, 33, 125, -47, -91, 79, -78, 10, 50, -50, 0, 0, 10, 117, 73, 68, 65, 84, 120, -100, -19, -35, -39, 114, -29, 54, 16, 0, -64, -91, -114, -4, -1, 39, -57, 54, -93, -40, 107, 91, 20, 9, -30, 26, -96, -5, 33, 85, 73, -86, 82, 90, -50, 104, 14, -128, 118, -2, -4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, -121, -27, -51, -3, 126, -7, -49, -3, -2, -2, -9, -83, 63, 19, 53, 44, -9, -53, -19, -6, -49, 19, -41, -37, -27, 46, 15, 70, -75, 25, 122, 105, 48, -74, -73, -32, -17, -118, -3, -105, 44, -112, 4, -61, 88, -114, 6, -1, 51, 9, -28, 64, 120, -9, 91, 90, -16, 31, 110, -9, -42, 127, 2, -46, 45, -105, 115, -47, 95, -87, 3, 49, 37, 87, -2, -97, -12, -126, 120, -14, 69, -1, -111, 3, -83, -1, 68, 28, -112, 59, -4, 82, 32, -110, 60, -99, -1, 55, 58, 65, 0, -27, -62, 47, 5, 2, 40, 27, 126, 41, -48, -69, -30, -31, -1, 72, -127, -42, 127, 74, -98, -72, 23, 24, -3, 126, 115, 117, 56, -44, -93, -27, -28, -103, -33, 17, 55, 125, -96, 59, 85, -86, -1, 39, 125, -96, 47, 75, -91, -22, -1, 73, 31, -24, 73, -59, -22, -1, 73, 31, -24, 69, -3, -81, -1, -22, 42, 3, -70, 80, -71, -5, 127, 101, 18, -24, 64, -109, -14, -1, -96, 13, -76, -42, -86, -4, 63, 104, 3, 109, 53, 44, -1, 15, -38, 64, 67, 77, -53, -1, -61, -83, -11, 83, -104, 87, -29, -14, -1, 112, 109, -3, 28, 38, -43, -70, -3, 127, 50, 8, -76, -48, 79, -4, 101, 64, 11, 61, -59, 95, 6, -44, -73, -76, 14, -7, 119, 50, -96, -86, 123, -21, 120, -1, -28, 114, -88, -94, 14, -29, 47, 3, 42, -22, -82, -2, -81, 116, -127, 74, 58, -115, -65, 12, -88, -92, -81, -7, -1, 43, -69, 64, 13, -3, -58, 95, 6, 84, -47, 113, -4, -99, 10, 87, -48, 117, -4, 101, 64, 113, 93, -36, -1, 109, 113, 55, 88, 84, 7, -9, -1, -81, 120, 63, -96, -96, 110, 23, -64, -81, 12, -126, -59, -12, -68, 0, 124, -78, 10, 20, -45, -3, 0, -80, 50, 6, 20, 18, 96, 0, 88, 25, 3, -118, 8, 49, 0, -84, 52, -127, 2, 98, 12, 0, 43, 99, 64, 1, 65, 6, -128, -107, 49, 32, -69, 46, 95, 1, 120, -50, -53, 1, -71, 5, 106, 0, -17, 28, 9, 103, 22, 102, 3, 120, -80, 9, 100, 21, 104, 3, 120, 48, 7, -26, 20, 106, 2, 92, -103, 3, 51, 10, 54, 1, -82, -52, -127, -7, 4, -101, 0, 87, -26, -64, 108, -62, 77, -128, 43, 115, 96, 38, 1, 39, -64, -107, 57, 48, -113, -96, 5, 64, 9, -56, 36, 108, 1, 80, 2, -14, 8, 91, 0, -108, -128, 44, 2, 23, 0, 37, 32, -121, -64, 5, 64, 9, -56, 32, 116, 1, 80, 2, -50, 11, 93, 0, -108, -128, -13, 90, 71, -16, -84, -42, -49, 47, -70, -32, 5, 64, 9, 56, 43, -28, 45, -64, 87, 110, 4, 78, 9, 62, 2, -66, 51, 6, -98, 17, -66, 3, -24, 1, -25, -124, -17, 0, 122, -64, 41, 3, 116, 0, 61, -32, -116, -128, 111, -126, -3, -28, -35, -80, 116, -83, 99, -105, 71, -21, -89, 24, 87, -56, 87, 1, 127, -14, 114, 96, -86, 33, 58, -128, 30, -112, -82, 117, -28, 114, 105, -3, 28, -93, 26, -92, 3, -24, 1, -87, 6, 56, 5, 90, 57, 11, 74, 51, -64, 41, -48, -54, 89, 80, -110, 33, 78, -127, 86, -50, -126, 82, 12, 51, 2, 24, 2, -46, 12, 51, 2, 24, 2, -46, 12, 51, 2, 24, 2, -110, 12, 52, 2, 24, 2, 82, 12, 52, 2, 24, 2, 82, 12, 52, 2, 24, 2, 82, 12, 114, 17, -80, 114, 29, 112, -36, 64, 51, -96, 41, 48, 69, -21, -104, -27, -43, -6, 105, -58, 51, -44, 18, 96, 13, 56, 110, -88, 37, -64, 26, 112, -36, 80, 75, -128, 53, -32, -72, -95, -106, 0, 107, -64, 113, 67, 45, 1, -42, -128, -29, 90, 71, 44, -73, -42, -49, 51, -102, -63, -106, 0, 107, -64, 81, 18, 96, 114, 18, 96, 114, 18, 96, 114, -125, -99, 3, 57, 9, 58, 74, 2, 76, 78, 2, 76, 110, -80, -109, 96, 103, -63, 71, 73, -128, -55, 73, -128, -55, 73, -128, -55, 73, -128, -55, 73, -128, -55, 73, -128, -55, 73, -128, -55, 73, -128, -55, 57, 9, -100, -100, 4, -104, -100, 4, -104, -100, -9, 1, 38, 39, 1, 102, -41, 58, 96, -71, -75, 126, -98, -31, -8, -71, -128, -55, 73, -128, -55, -7, -47, -80, -55, 13, 118, 20, -24, 32, -16, -88, -63, 14, 2, 28, 3, 28, 53, -40, 30, 104, 11, 60, 74, 2, -52, -82, 117, -56, -14, 106, -3, 52, 3, 26, 106, 15, -76, 5, 30, 55, -44, 26, 96, 9, 56, 110, -88, 53, -64, 18, 112, -36, 80, 83, -96, 25, 48, -63, 64, 67, -128, 17, 32, -59, 64, -121, -63, 14, -126, 83, 12, 52, 5, -102, 1, 83, 12, 52, 4, 24, 1, -110, 12, 51, 4, 24, 1, -46, 12, -45, 3, 116, -128, 52, -61, -100, 4, 56, 5, 72, -44, 58, 112, -71, -76, 126, -114, 97, 13, -78, 8, 90, 2, 83, 13, -46, 3, 116, -128, 100, -83, 67, -105, 71, -21, -89, 24, -40, 16, 61, 64, 7, 72, 55, 68, 15, -48, 1, 78, 104, 29, -68, 28, 90, 63, -61, -48, 6, 56, 11, 114, 10, 116, -58, 0, -9, 1, -18, 1, 78, 9, 127, 31, -32, 30, -32, -100, -16, 99, -96, 17, -16, -92, -42, 1, 60, -85, -11, -13, 11, 47, -8, 24, 104, 4, 60, 43, -8, 24, 104, 4, 60, 45, -12, 105, -96, 83, -64, -13, 66, -105, 0, 5, 32, -125, -64, 83, -128, 9, 32, -121, -64, 37, 64, 1, -56, 34, 108, 9, 80, 0, -14, 8, 91, 2, 20, -128, 76, -126, -106, 0, 5, 32, -105, -96, 37, 64, 1, -56, 38, 100, 9, 80, 0, 50, 10, 120, 41, -24, 26, 48, -89, -128, 77, 64, 3, -56, 42, 92, 19, -48, 0, 50, 11, -42, 4, 52, -128, -36, -126, -67, 25, -30, 61, -112, -20, 66, -35, 10, -70, 5, -52, 111, 9, -44, 4, -82, 38, -64, 2, 2, 109, 2, -30, 95, 68, -104, 77, -64, 6, 80, 72, -112, 49, -64, 0, 80, 74, -116, 49, -64, 0, 80, 78, -120, 49, 64, -4, 11, 10, 48, 6, 24, 0, -118, -22, 126, 12, 48, 0, 20, -42, 121, 6, -120, 127, 113, 93, 15, -126, -82, 0, -54, -21, 121, 21, -80, 0, -44, -48, -15, 42, 32, -2, 85, 116, -101, 1, -30, 95, 73, -89, 25, 32, -2, -43, 116, -103, 1, -30, 95, 81, -121, 25, 32, -2, 85, 117, -105, 1, -30, 95, 89, 95, -37, -96, -3, -81, -66, -98, 50, 64, -4, 91, -24, 39, 3, -60, -65, -111, 78, -18, 5, -100, -1, 55, -45, -59, -19, -80, -5, -33, -122, 58, -8, 105, 1, 63, 1, -48, 84, -21, 65, 64, -5, 111, 109, 105, 58, 8, -36, -60, -65, -67, -122, -125, -128, -10, 95, -47, 114, 121, -10, 109, 107, -43, 6, -98, -106, -1, -25, 31, -107, 100, -17, -33, -13, -89, -113, -75, 73, 17, 120, -6, -11, 95, -74, -2, 37, 73, -18, -21, -105, -4, -23, -60, 93, -65, 8, 60, -97, -2, -42, -51, -28, 106, 59, -56, -25, 51, -68, -49, -97, 106, -27, 34, -16, -4, 27, -2, -1, 102, 106, 65, -56, -28, -81, 57, -1, -7, -125, -81, 89, 4, 54, 98, -5, 53, 17, -19, 8, 57, 124, -5, 106, 111, 52, -41, 123, -91, 20, -40, -86, -18, -5, 63, 45, -69, -4, -4, 94, 111, -99, -68, 87, -23, 3, 91, 65, -3, 113, 40, -95, 15, -100, -13, 91, 68, -73, 50, 96, 41, -98, 2, -101, 27, -34, 111, -121, 82, -118, 64, -70, 39, 53, 125, -13, 91, 85, -10, 100, 112, -77, -85, 63, -103, 66, -20, 3, -119, 54, 66, -71, -7, 72, -53, 85, -127, -19, -13, -99, -25, 23, 83, -122, -63, 20, -101, 23, 125, -37, 117, 117, -71, 20, 24, 7, -81, 47, -114, -9, 54, -45, 78, 17, 56, -22, 85, 37, 127, 53, 92, -27, -34, 8, 94, -43, -15, 87, 75, -88, 34, 112, -52, -114, 123, -2, 87, 95, -86, -100, -61, -64, -53, -16, 101, -8, -68, 124, -79, -85, -117, -65, 30, -81, -17, 89, 114, -32, -10, 58, 116, -103, 62, 47, -85, -67, 103, 122, 123, 118, -20, -77, 57, -80, 35, -6, 89, 63, 47, -121, 94, -13, -38, -13, -91, 90, -18, -73, -60, 121, -32, 122, -69, -17, 9, -39, -127, -91, 67, 27, -40, -31, -48, 87, 118, -25, -105, -22, 120, 18, -20, 12, -2, -47, 43, 8, 109, -32, -107, -61, 87, 58, -69, -57, -21, -27, -66, 119, 57, -68, 94, 118, 6, 63, 97, -46, -44, 6, -74, -91, -68, -27, 123, -28, 91, -75, -68, -91, -63, -13, 98, 112, -67, -67, -123, -2, 72, -124, 82, -114, -100, -76, -127, 13, 105, 103, 120, -57, -65, 85, -53, 91, 34, -36, -17, -105, 55, -73, -37, -5, 95, -33, -2, 102, 57, 20, -7, -11, -65, -110, 54, 91, 104, 3, 79, 37, 79, -20, 45, -34, -63, 75, 63, 113, -10, -109, 68, -65, 59, -11, 70, 71, -19, 20, 56, 117, -31, 96, 16, -8, -51, -39, -97, -8, -81, -103, 2, 103, -17, -101, 100, -64, 79, 25, 126, -56, -85, 86, 10, -28, -72, 110, 52, 10, 126, -109, -25, 10, -73, 70, 10, 100, -70, 109, 54, 10, -2, 37, -37, -59, 77, -23, 91, -73, 124, 87, 76, 70, -63, 47, 114, -66, -59, -77, -25, -12, 62, 85, -98, -37, -91, -57, 7, 45, -9, 57, -125, -55, -2, 66, 119, -103, 50, -112, -3, 101, 51, -93, -32, -86, -60, 11, -3, -81, 94, -33, 57, -2, 33, -117, -68, 100, 36, 3, -2, -108, -5, -127, -114, -116, 57, 80, 36, -6, 31, -97, 81, 6, 20, -3, -127, -98, 91, -114, 28, 88, 46, 5, -33, 51, -106, 1, -59, 127, -31, -29, -87, 36, 40, 26, -4, -43, -28, 25, 80, -27, 23, 126, 94, -109, -110, 96, -39, -72, 54, -52, 105, -22, 12, -88, -9, 11, 95, -33, -110, -32, -78, -9, -66, 111, 89, 46, -107, -126, -1, 97, -30, 12, -88, -1, 11, 127, 63, 110, -4, 55, 62, -48, -42, -37, 2, -59, 76, -101, 1, -19, 126, -31, -13, -11, -51, -29, 77, -128, -11, -83, -128, -9, 127, -46, -20, -29, 76, -102, 1, -83, 127, -61, 91, 63, -26, -36, 5, -60, -1, -45, -116, 25, 32, -2, 95, -51, -105, 1, -30, -1, -73, -23, 50, 64, -4, -65, -103, -20, 127, 57, -39, -55, 47, 122, -17, -55, 84, -73, -61, 93, -4, -102, -9, -34, 76, -12, -114, 80, 7, -65, -28, -67, 71, -45, -68, 39, -40, -35, -1, -15, -85, 23, -109, 12, -126, -30, -1, -44, 20, 25, 96, 1, 124, 110, -118, 101, 80, -4, 55, 76, -80, 12, 90, 0, 54, 13, -65, 10, 88, 0, 94, 24, 124, 21, 48, 0, -66, 52, -10, 24, 96, 0, 120, 105, -24, 49, -64, 9, -16, 14, 3, -97, 9, 27, 0, 119, 25, 118, 12, 48, 0, -20, 52, -24, 24, -32, 4, 104, -81, 65, -57, 0, 3, -64, 110, 67, -114, 1, 6, -128, 3, 6, 28, 3, 12, 0, -121, -116, 55, 6, 24, 0, 14, 25, -82, 9, 104, 0, 7, 13, -42, 4, 52, -128, -61, -58, 106, 2, 54, -128, -61, -122, 106, 2, -18, 0, 19, 12, -44, 4, 52, -128, 36, -29, 52, 1, 13, 32, -55, 48, 77, 64, 3, 72, 52, 74, 19, 112, 4, -112, 104, -112, 59, 1, 71, 0, -55, -122, 120, 67, -48, 4, 120, -62, 8, 115, -96, 9, -16, -124, 1, -26, 64, 19, -32, 41, -15, -25, 64, 19, -32, 41, -31, -25, 64, 19, -32, 73, -63, -25, 64, 19, -32, 105, -79, -25, 64, 5, -32, -76, -48, 37, 64, 1, -56, 32, 114, 9, -80, 2, 102, 16, 120, 21, 84, 0, -78, -120, 91, 2, 20, -128, 44, -62, -106, 0, 5, 32, -109, -88, 37, 64, 1, -56, 36, 104, 9, 80, 0, -78, -119, 89, 2, 20, -128, 108, 66, -106, 0, 5, 32, -93, -120, 37, 64, 1, -56, 40, 96, 9, 80, 0, -78, -118, 87, 2, -36, 2, 100, 21, -17, 70, -96, -11, 19, 27, 77, -21, 120, 30, -91, 0, 100, 22, -83, 4, 120, 17, 40, -77, 96, -81, 6, 121, 19, 48, -69, 88, 111, 7, -38, 1, -77, 11, -75, 9, -38, 1, 11, -120, -76, 9, 26, 1, 11, -120, 52, 6, -74, 126, 86, 99, 106, 29, -43, -3, 20, -128, 34, -30, -108, 0, 59, 96, 17, 97, 54, 65, 35, 96, 33, 81, -58, 64, 29, -96, -112, 40, 61, 64, 7, 40, 36, 72, 15, -48, 1, -118, -119, -47, 3, 116, -128, 98, 98, -12, 0, 29, -96, -104, 16, 61, -64, 61, 80, 65, 17, 110, -124, -36, 3, 21, 20, -31, 70, -88, -11, 51, 26, 91, -21, -24, -66, -90, 3, 20, -43, 127, 15, -80, 3, 20, -43, -1, 30, 96, 7, 40, -86, -5, 61, -64, 41, 80, 97, -67, -97, 5, -23, 0, -123, -11, -34, 3, 116, -128, -62, 58, -17, 1, 58, 64, 113, 125, -9, 0, 75, 96, 113, 125, 47, -126, -114, 1, -117, -21, -5, 48, -80, -11, -45, -103, 65, -21, 24, 111, 49, 2, 84, -48, -13, 16, 96, 9, -84, -96, -25, 69, -48, 8, 80, 65, -49, 67, -128, 83, -128, 10, 58, 62, 9, 48, 2, 84, -47, -17, 16, -32, 20, -96, -118, 126, 79, 2, -52, -128, 85, -12, 59, 5, 26, 1, -86, -24, 119, 8, 104, -3, 100, 102, -47, 58, -50, -49, -104, 1, 43, -23, 117, 10, 52, 2, 84, -46, -21, 16, -32, 24, -88, -110, 94, -113, -126, -52, -128, -107, -12, 58, 5, -74, 126, 46, -13, 104, 29, -23, -33, -103, 1, -85, -23, 115, 10, 116, 14, 88, 77, -97, 103, -127, -106, -128, 106, -6, 92, 3, 44, 1, -43, -12, -71, 6, 88, 2, -86, -23, 115, 13, 104, -3, 84, 102, -46, 58, -42, -65, -79, 4, 84, -44, -29, 26, 96, 9, -88, -88, -57, 53, -64, 18, 80, 81, -113, 107, -128, 4, -88, 72, 2, 76, 78, 2, 76, 78, 2, 76, -82, -57, 4, -80, 5, 84, -44, -29, 22, -32, 28, -96, -94, 30, -49, 1, -36, 5, -44, -45, -25, 93, -128, 18, 80, 77, -105, 5, -64, 24, 88, 77, -113, 35, -32, 7, -9, -127, 85, -12, 121, 23, -8, 110, -111, 1, 21, 92, 59, 109, 0, -17, 100, 64, 121, 61, -57, -1, -113, 57, -96, -72, 110, -5, -1, -61, 98, 27, 44, -24, -42, -9, -41, -1, 63, -9, -53, 85, 43, -56, -18, 122, -67, -12, 120, -2, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -111, -3, 11, -59, -40, 29, -72, 124, -80, 124, 23, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126]
            response.contentType = "image/jpeg"
            response.contentLength = temp.size()
            response.outputStream << temp
        }
    }


    def activation(UserCommand command){
        command.enabled = Boolean.parseBoolean(params.status);
        User user = userService.updateActivation(command)
        user
    }

    def userloanrequest(Long id){
        redirect(controller: 'adminLoanRequest',  action: 'userrequests', id: id)
    }

    def forgetpassword(){}

    def sendresetpassword(){
        def result = userService.resetPassword(params.email)
        if(result!=null){
            flash.message = message(code: 'userController.resetpassword.found')
            render(view: '/login/auth')
            return
        }else{
            flash.message = message(code:'userController.resetpassword.notfound')
            render(view: 'forgetpassword',status: NOT_FOUND)
            return
        }
    }

    def resetpassword(String token){
        User user = userService.checkResetToken(token)
        if(user !=null){
            params.id = user.id
            render(view: 'resetpassword', model:[params: params])
        }else{
            flash.message = message(code:'userController.invalidtoken',status: NOT_FOUND)
            render(view: '/login/auth')
        }

    }

    def reset(){
        User user = userService.reset(params.long('id'),params.password)
        if(user==null){
            flash.message = message(code:'userController.invalidtoken',status: NOT_FOUND)
            render(view: '/login/auth')
        }
        redirect(action: 'show', id: user.id)
    }

}
