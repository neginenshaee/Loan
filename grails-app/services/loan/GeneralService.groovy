package loan

import grails.util.Environment
import org.springframework.util.StringUtils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

class GeneralService {

    def grailsApplication

    Locale environmentLocale = Locale.ENGLISH

    public def getMessage(String code, Locale locale) {
        try {
            def appCtx = grailsApplication.getMainContext()
            return appCtx.getMessage(code, null, locale ? locale : environmentLocale)
        }
        catch (Exception ex) {
            return "WARNING! EMPTY MESSAGE"
        }
    }

    public def getMessage(String code) {
        try {
            def appCtx = grailsApplication.getMainContext()
            return appCtx.getMessage(code, null, environmentLocale)
        }
        catch (Exception ex) {
            return "WARNING! EMPTY MESSAGE"
        }
    }

    public def getMessage(String code, Object[] args) {
        try {
            def appCtx = grailsApplication.getMainContext()
            return appCtx.getMessage(code, args, environmentLocale)
        }
        catch (Exception ex) {
            return "WARNING! EMPTY MESSAGE"
        }
    }

    def getMessage(String code, Object[] args, Locale locale) {
        try {
            def appCtx = grailsApplication.getMainContext()
            return appCtx.getMessage(code, args, locale)
        }
        catch (Exception ex) {
            return "WARNING! EMPTY MESSAGE"
        }
    }


}