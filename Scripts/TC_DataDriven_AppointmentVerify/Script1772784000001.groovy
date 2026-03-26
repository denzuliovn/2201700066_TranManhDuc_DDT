import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
 
// ====== STEP 1: Mở trình duyệt và login ======
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com')
WebUI.maximizeWindow()
 
// Click nút Make Appointment để vào trang login
WebUI.click(findTestObject('Page_CURA/btn_MakeAppointment'))
 
// Login với tài khoản demo
WebUI.setText(findTestObject('Page_CURA/txt_Username'), 'John Doe')
WebUI.setText(findTestObject('Page_CURA/txt_Password'), 'ThisIsNotAPassword')
WebUI.click(findTestObject('Page_CURA/btn_Login'))
 
// Xác nhận đã vào trang Make Appointment
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_MakeAppointment'), 10)
 
// ====== STEP 2: Điền form đặt lịch hẹn từ Data File ======
// Chọn cơ sở y tế
WebUI.selectOptionByLabel(findTestObject('Page_CURA/select_Facility'), facility, false)
 
// Tick checkbox Hospital Readmission nếu cần
if (readmission == 'Yes') {
    WebUI.check(findTestObject('Page_CURA/chk_HospitalReadmission'))
}
 
// Chọn chương trình bảo hiểm
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
 
// Click nút Book Appointment
WebUI.click(findTestObject('Page_CURA/btn_BookAppointment'))
 
// ====== STEP 3: Xác minh trang xác nhận ======
WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_AppointmentConfirmation'), 10)
 
// Xác minh Facility hiển thị đúng
String actualFacility = WebUI.getText(findTestObject('Page_CURA/p_Facility'))
WebUI.verifyMatch(actualFacility, facility, false)
WebUI.comment('Facility verified: ' + actualFacility)
 
// Xác minh Program hiển thị đúng
String actualProgram = WebUI.getText(findTestObject('Page_CURA/p_Program'))
WebUI.verifyMatch(actualProgram, program, false)
WebUI.comment('Program verified: ' + actualProgram)
 
// Xác minh Visit Date hiển thị đúng
String actualDate = WebUI.getText(findTestObject('Page_CURA/p_VisitDate'))
WebUI.verifyMatch(actualDate, visit_date, false)
WebUI.comment('Visit Date verified: ' + actualDate)
 
// Xác minh Comment hiển thị đúng
String actualComment = WebUI.getText(findTestObject('Page_CURA/p_Comment'))
WebUI.verifyMatch(actualComment, comment, false)
WebUI.comment('Comment verified: ' + actualComment)
 
WebUI.comment('>>> TEST PASSED: Appointment booked and verified for ' + facility)
 
WebUI.closeBrowser()
