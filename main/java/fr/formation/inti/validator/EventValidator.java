package fr.formation.inti.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.client.RestTemplate;

import fr.formation.inti.entity.Evenement;
import fr.formation.inti.model.Location;

@Component
public class EventValidator implements Validator{
	


		@Override
		public void validate(Object obj, Errors errors) {

			Evenement event = (Evenement) obj;
			
			
			if (event.getEvenementAdress()!=null) {
				Location location;
				
				String name = event.getEvenementAdress()+" "+event.getEvenementCity();
				location = new Location(name);
				System.out.println(name);

				RestTemplate restTemplate = new RestTemplate();

				ResponseEntity<String> topic_body = restTemplate.exchange("https://nominatim.openstreetmap.org/?addressdetails=1&q="+location.getName()+"&format=json&limit=1", 
						HttpMethod.GET, null, String.class );
			     		 
				String  topics = topic_body.getBody(); 
				System.out.println("test : " + topics);
				topics = topics.replace("\"address\":{", "");
				topics = topics.replace("[","");
				topics = topics.replace("]","");
				topics = topics.replace("}}","");
				topics = topics.replace("{","");
				
				List<String> test = new ArrayList<String>();
				
				String[] list = topics.split(",\"");
				Location l = new Location();
				
				for (int i = 0; i < list.length; i++) {
					
					String j =list[i].replace("\"", "");
					list[i] = j;

					 String[] list1 = list[i].split(":");
					 
			 		
					if (list1[0].equals("lat")  ) {
						test.add(list1[1]);
					}
					if ( list1[0].equals("lon") ) {
									
						test.add(list1[1]);
								}
					if ( list1[0].equals("place_id") ) {
						
						test.add(list1[1]);
					}
					if ( list1[0].equals("country") ) {
						
						test.add(list1[1]);
					}
				}
			if (test.size()==0) {
				errors.rejectValue("evenementAdress", "mauvaiseadresse");
			}}
//			
//			if (emp.getSuperiorid()!=null) {
//			if (emp.getSuperiorid() <= 0) {
//				errors.rejectValue("superiorid", "negativeValue", new Object[] { "'superiorid'" }, "Superiorid can't be negative");
//			}}

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "evenementName", "eventname.required");
			//rajouter un test pour le lieu
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.required");
//			ValidationUtils.rejectIfEmpty(errors, "startdate", "startdate.required");
		}

		@Override
		public boolean supports(Class<?> paramClass) {
			return Evenement.class.equals(paramClass);
		}

	


}