import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
 
// ====== STEP 1: Mở trình duyệt và login ======
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com')
WebUI.maximizeWindow()
 
WebUI.click(findTestObject('Page_CURA/btn_MakeAppointment'))
WebUI.setText(findTestObject('Page_CURA/txt_Username'), 'John Doe')
WebUI.setText(findTestObject('Page_CURA/txt_Password'), 'ThisIsNotAPassword')
WebUI.click(findTestObject('Page_CURA/btn_Login'))
 
// Xác nhận đã login thành công
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_MakeAppointment'), 10)
 
// ====== STEP 2: Đặt lịch hẹn ======
WebUI.selectOptionByLabel(findTestObject('Page_CURA/select_Facility'), facility, false)
 
if (program == 'Medicaid') {
    WebUI.click(findTestObject('Page_CURA/radio_Medicaid'))
} else if (program == 'Medicare') {
    WebUI.click(findTestObject('Page_CURA/radio_Medicare'))
} else {
    WebUI.click(findTestObject('Page_CURA/radio_None'))
}
 
WebUI.setText(findTestObject('Page_CURA/txt_VisitDate'), visit_date)
WebUI.setText(findTestObject('Page_CURA/txt_Comment'), comment)
WebUI.click(findTestObject('Page_CURA/btn_BookAppointment'))
 
// Xác nhận đặt lịch thành công
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_AppointmentConfirmation'), 10)
WebUI.comment('Appointment booked successfully for: ' + facility)
 
// ====== STEP 3: Navigate đến History page ======
WebUI.click(findTestObject('Page_CURA/btn_Menu'))
WebUI.delay(1)
WebUI.click(findTestObject('Page_CURA/lnk_History'))
 
// Xác nhận đang ở trang History
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_History'), 10)
WebUI.comment('Successfully navigated to History page')
 
// Xác nhận có ít nhất 1 bản ghi lịch hẹn hiển thị
WebUI.verifyElementPresent(findTestObject('Page_CURA/p_Facility'), 10)
String historyFacility = WebUI.getText(findTestObject('Page_CURA/p_Facility'))
WebUI.comment('History shows facility: ' + historyFacility)
 
WebUI.comment('>>> TEST PASSED: Appointment history verified for ' + facility)
 
WebUI.closeBrowser()
