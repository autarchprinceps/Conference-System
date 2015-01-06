package conference.viewControllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ConferenceSearchController {
    public List<String> doAutocomplete(String query) {
        // TODO
        return new ArrayList<>();
    }
}
