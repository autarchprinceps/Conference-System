/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface SearchLocal {

	List<String> getConferenceAutocompletion(final String query);

	List<String> getPubAutocomplete(final String query);
	
}
