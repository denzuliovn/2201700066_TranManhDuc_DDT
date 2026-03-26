import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
 
// ====== STEP 1: Mở trình duyệt và thực hiện login ======
WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com')
WebUI.maximizeWindow()
 
WebUI.click(findTestObject('Page_CURA/btn_MakeAppointment'))
 
// Điền thông tin login từ Data File
WebUI.setText(findTestObject('Page_CURA/txt_Username'), username)
WebUI.setText(findTestObject('Page_CURA/txt_Password'), password)
WebUI.click(findTestObject('Page_CURA/btn_Login'))
 
// ====== STEP 2: Kiểm tra kết quả login ======
if (expected_result == 'success') {
    // Xác nhận login thành công
    WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_MakeAppointment'), 10)
    WebUI.comment('Login thành công với: ' + username)
 
    // ====== STEP 3: Navigate đến Profile page ======
    WebUI.click(findTestObject('Page_CURA/btn_Menu'))
    WebUI.delay(1)
    WebUI.click(findTestObject('Page_CURA/lnk_Profile'))
 
    // Xác nhận đang ở trang Profile
    WebUI.verifyElementPresent(findTestObject('Page_CURA/h2_Profile'), 10)
    WebUI.comment('Successfully navigated to Profile page')
 
    // ====== STEP 4: Logout ======
    WebUI.click(findTestObject('Page_CURA/btn_Logout'))
    WebUI.delay(1)
 
    // Xác nhận đã logout - quay về trang chủ
    WebUI.verifyElementPresent(findTestObject('Page_CURA/btn_MakeAppointment'), 10)
    WebUI.comment('Logout thành công')
 
    WebUI.comment('>>> TEST PASSED: Login + Profile + Logout thành công với: ' + username)
} else {
    // Xác nhận login thất bại
    WebUI.verifyElementPresent(findTestObject('Page_CURA/txt_LoginFailed'), 10)
    WebUI.comment('>>> TEST PASSED: Login thất bại như mong đợi với: ' + username)
}
 
WebUI.closeBrowser()
