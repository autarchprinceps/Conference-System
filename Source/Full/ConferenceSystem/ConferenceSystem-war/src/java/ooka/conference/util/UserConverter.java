/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.User;

@ManagedBean(name = "userConverter")
public class UserConverter implements Converter {

    @EJB
    private SearchLocal searchEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return searchEJB.searchUserById(Integer.parseInt(value));
        // return Integer.parseInt(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        User user = (User) value;
        return user.getId().toString();
        // return value.toString();
    }

}
