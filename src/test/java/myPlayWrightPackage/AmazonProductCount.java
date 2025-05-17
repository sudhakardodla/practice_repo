package myPlayWrightPackage;

import com.microsoft.playwright.*;

import java.util.List;
import java.util.Scanner;

public class AmazonProductCount {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a product to search");
        String pro = sc.nextLine();
        sc.close();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.amazon.in");
            page.locator("#twotabsearchtextbox").fill(pro);
            page.locator("input[id*='submit-button']").click();
            page.waitForTimeout(3000);

            int trc = 0;
            int pgno = 2;

            while (true) {
                List<ElementHandle> proList = page.querySelectorAll("div[role='listitem']");
                int prc = 0;
                for (ElementHandle ele : proList) {
                    try {
                        if (ele.isVisible()) {
                            prc++;
                        }
                    } catch (Exception ex) {
                        // Ignore invisibles
                    }
                }
                trc += prc;

                try {
                    Locator nextPage = page.locator("a[aria-label='Go to page " + pgno + "']");
                    if (nextPage.isVisible()) {
                        nextPage.click();
                        page.waitForTimeout(3000);
                        pgno++;
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            System.out.println("Total product count: " + trc);
            System.out.println("Press Enter to close...");
            System.in.read();
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
