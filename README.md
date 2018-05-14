# Routes-User-Input-Group1

public class MyHttpSessionListener implements HttpSessionListener{
  public void sessionCreated(HttpSessionEvent event){
    event.getSession().setMaxInactiveInterval(15*60); //in seconds
  }
  public void sessionDestroyed(HttpSessionEvent event){}
}
