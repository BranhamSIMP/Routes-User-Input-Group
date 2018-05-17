/*
Implementations of this interface are invoked at their sessionCreated(javax.servlet.http.HttpSessionEvent)
method in the order in which they have been declared, and at their sessionDestroyed(javax.servlet.http.HttpSessionEvent)
method in reverse order.
*/

public class MyHttpSessionListener implements HttpSessionListener{
  public void sessionCreated(HttpSessionEvent event){
    event.getSession().setMaxInactiveInterval(15*60); //in seconds
  }
  public void sessionDestroyed(HttpSessionEvent event){}
}
