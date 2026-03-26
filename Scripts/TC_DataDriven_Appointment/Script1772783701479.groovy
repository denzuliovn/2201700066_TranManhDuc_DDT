import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Login trước
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com')
WebUI.maximizeWindow()

WebUI.click(findTestObject('Page_CURA/btn_MakeAppointment'))

WebUI.setText(findTestObject('Page_CURA/txt_Username'), 'John Doe')

WebUI.setText(findTestObject('Page_CURA/txt_Password'), 'ThisIsNotAPassword')

WebUI.click(findTestObject('Page_CURA/btn_Login'))

// Đặt lịch hẹn với dữ liệu từ file CSV
WebUI.selectOptionByLabel(findTestObject('Page_CURA/select_Facility'), facility, false)

// Chọn chương trình bảo hiểm (Healthcare Program)
if (program == 'Medicaid') {
    WebUI.click(findTestObject('Page_CURA/radio_Medicaid'))
} else if (program == 'Medicare') {
    WebUI.click(findTestObject('Page_CURA/radio_Medicare'))
} else {
    WebUI.click(findTestObject('Page_CURA/radio_None'))
}

// Điền ngày khám và ghi chú
WebUI.setText(findTestObject('Page_CURA/txt_VisitDate'), visit_date)

WebUI.setText(findTestObject('Page_CURA/txt_Comment'), comment)

// Đặt lịch hẹn
WebUI.click(findTestObject('Page_CURA/btn_BookAppointment'))

// Xác nhận kết quả
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_AppointmentConfirmation'), 10)

WebUI.comment((('Appointment booked: ' + facility) + ' - ') + visit_date)

WebUI.closeBrowser()

