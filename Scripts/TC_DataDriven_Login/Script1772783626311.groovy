import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
 
// Mở trình duyệt và điều hướng đến trang CURA Healthcare
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com')
WebUI.maximizeWindow()
 
// Click nút Make Appointment
WebUI.click(findTestObject('Page_CURA/btn_MakeAppointment'))
 
// Điền thông tin login từ biến (sẽ được truyền từ Data File)
WebUI.setText(findTestObject('Page_CURA/txt_Username'), username)
WebUI.setText(findTestObject('Page_CURA/txt_Password'), password)
 
// Click nút Login
WebUI.click(findTestObject('Page_CURA/btn_Login'))
 
// Kiểm tra kết quả dựa trên expected_result
if (expected_result == 'success') {
    WebUI.verifyElementPresent(
        findTestObject('Page_CURA/h2_MakeAppointment'),
        10
    )
    WebUI.comment('Login thành công với: ' + username)
} else {
    WebUI.verifyElementPresent(
        findTestObject('Page_CURA/txt_LoginFailed'),
        10
    )
    WebUI.comment('Login thất bại như mong đợi với: ' + username)
}
 
WebUI.closeBrowser()
