package com.example.javafx;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloController {
    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button executeButton;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    /**
     * 특정주소를 호출하는 웹브라우저 호출
     */
    private HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    private void onExecuteButtonClick() {
        String inputText = inputField.getText();
        appendToOutput("Input: " + inputText);
        // 여기서 원하는 동작을 수행하고 결과를 TextArea에 추가할 수 있습니다.
        // 예: 어떤 계산 수행, 외부 서비스 호출 등
    }

    @FXML
    private void onButton1Click() {
        appendToOutput("Button 1 clicked");
        // 버튼 1에 대한 동작 수행

//        if (hostServices != null) {
//            hostServices.showDocument("http://www.naver.com");
//        }
        openWebBrowser("http://www.naver.com");
    }

    @FXML
    private void onButton2Click() {
        appendToOutput("Button 2 clicked");
        // 버튼 2에 대한 동작 수행
        // 120.0.6099.110(공식 빌드) (64비트)
        // https://chromedriver.chromium.org/downloads
        // https://googlechromelabs.github.io/chrome-for-testing/
        // WebDriver 경로 설정 (ChromeDriver를 사용하는 예제)
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver-win64\\chromedriver.exe");

        // WebDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver();

        try {
            // 네이버 검색 페이지 열기
            driver.get("https://www.naver.com");

            // 검색 입력란 찾기
            WebElement searchInput = driver.findElement(By.name("query"));

            // 검색 입력란에 텍스트 입력
            searchInput.sendKeys("Hello, ChatGPT");

            // 몇 초간 대기 (화면을 확인하기 위해)
//            Thread.sleep(5000);
            Thread.sleep(3600000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
//            driver.quit();
            driver.close();
        }

    }

    @FXML
    private void onButton3Click() {
        appendToOutput("Button 3 clicked");
        // 버튼 3에 대한 동작 수행
    }

    @FXML
    private void onButton4Click() {
        appendToOutput("Button 4 clicked");
        // 버튼 4에 대한 동작 수행
    }

    private void appendToOutput(String text) {
        outputArea.appendText(text + "\n");
    }

//    private void openWebBrowser(String url) {
//        try {
//            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//                Desktop.getDesktop().browse(new URI(url));
//            } else {
//                // 웹 브라우저 열기를 지원하지 않는 환경에서 대체 동작을 수행
//                // 예: 로그 출력 또는 다른 방법으로 링크 열기
//                System.out.println("Web browser not supported. Open the following URL manually: " + url);
//            }
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

    private void openWebBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Windows
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Linux or Mac
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                // Other OS
                System.out.println("Unsupported operating system");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}