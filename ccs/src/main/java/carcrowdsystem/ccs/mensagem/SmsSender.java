//package carcrowdsystem.ccs.mensagem;
//import com.twilio.Twilio;
//import com.twilio.exception.ApiException;
//import com.twilio.rest.api.v2010.Account;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//@RestController
//@RequestMapping("${uri.dev}/checkin")
//public class SmsSender {
//
//    public static final String ACCOUNT_SID = "ACd9e5fd36b49f20db2bd059fa5430c364";
//    public static final String AUTH_TOKEN = "14c1415db4aaf5c0014bb1c002fe2d4f";
//
//    @PostMapping("/mensagem/{destinatario}")
//    public void enviarMensagem(@PathVariable String destinatario){
//        try{
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        }catch (ApiException ex){
//            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(),null);
//        }
//
//        try {
//            Message message = Message.creator(
//                          new com.twilio.type.PhoneNumber("+55" + destinatario),
//                          new com.twilio.type.PhoneNumber("+13157125539"),
//                          "Apenas teste")
//                  .create();
//
//                System.out.println(message.getSid());
//        }catch (ApiException ex){
//            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(),null);
//        }
//    }
//}