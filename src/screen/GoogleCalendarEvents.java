package screen;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.util.List;

public class GoogleCalendarEvents {

    public static List<Event> getEvents() {
        try {
            Calendar service = GoogleCalendarAuth.getCalendarService();

            if (service == null) {
                System.out.println("Erro ao autenticar ou obter o serviço do Google Calendar.");
                return null;
            }

            Events events = service.events().list("primary")
                    .setMaxResults(50)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            return events.getItems();
        } catch (IOException e) {
            System.out.println("Erro ao carregar eventos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
