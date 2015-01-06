package conference.viewControllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PubSearchController {
    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;
}
