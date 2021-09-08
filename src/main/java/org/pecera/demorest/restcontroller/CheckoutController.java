package org.pecera.demorest.restcontroller;



import com.paypal.core.PayPalHttpClient;
import com.paypal.http.Encoder;
import org.pecera.demorest.paymentgateways.PaypalGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;

@RestController
public class CheckoutController {

    private static String DEFAULT_CONFIG_FILENAME = "config.properties";
    private static PayPalHttpClient payPalHttpClient;
    private static PaypalGateway paypalGateway;

    @Autowired
    public CheckoutController(PaypalGateway paypalGateway) {
        try {
            CheckoutController.paypalGateway = paypalGateway;
            payPalHttpClient = paypalGateway.getClient(new File(DEFAULT_CONFIG_FILENAME));
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) {
        return "redirect:checkouts";
    }

    @RequestMapping(value = "/checkouts", method = RequestMethod.GET)
    public String checkout(Model model) {
        Encoder encoder = payPalHttpClient.getEncoder();
        return encoder.getClass().toString();
    }

    @RequestMapping(value = "/checkouts", method = RequestMethod.POST)
    public String postForm(@RequestParam("amount") String amount, Model model, final RedirectAttributes redirectAttributes) {
        return paypalGateway.getOrder(amount, payPalHttpClient,redirectAttributes);
    }



    @RequestMapping(value = "/checkouts/{transactionId}")
    public String getTransaction(@PathVariable String transactionId, Model model) {


        return "checkouts/show";
    }
}