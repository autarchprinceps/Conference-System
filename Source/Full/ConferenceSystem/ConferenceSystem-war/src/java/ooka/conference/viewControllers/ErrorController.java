package ooka.conference.viewControllers;

import ooka.conference.util.Message;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ErrorController {
    
    private Message errorMsg;

    public Message getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Message errorMsg) {
        this.errorMsg = errorMsg;
    }


}
