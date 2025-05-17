package myPlayWrightPackage;

import java.util.List;

import com.microsoft.playwright.*;

public class AnchorTagCountPlaywright {
	    public static void main(String[] args) {
	        Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.cricbuzz.com/live-cricket-scorecard/112469/ind-vs-nz-final-icc-champions-trophy-2025");

            List<ElementHandle> elements = page.querySelectorAll("a:has-text(\"Virat\")");

            System.out.println("Total Elements Found: " + elements.size());
	    }
}