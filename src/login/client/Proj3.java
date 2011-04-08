package login.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;



public class Proj3 implements EntryPoint, ClickHandler
{
VerticalPanel mainPanel = new VerticalPanel();
TextArea textarea = new TextArea();
TextBox firstNameBox = new TextBox();
PasswordTextBox lastNameBox = new PasswordTextBox();
Button okButton = new Button("Submit");

public void onModuleLoad()
{
okButton.addClickHandler(this);
HorizontalPanel panel = new HorizontalPanel();
//panel.add(textarea);
//textarea.setCharacterWidth(50);
//textarea.setVisibleLines(25);
VerticalPanel inputPanel = new VerticalPanel();
Label fnLabel = new Label("Username:");
HorizontalPanel row1 = new HorizontalPanel();
row1.add(fnLabel);
row1.add(firstNameBox);
inputPanel.add(row1);
HorizontalPanel row2 = new HorizontalPanel();
Label lnLabel = new Label("Password:");
row2.add(lnLabel);
row2.add(lastNameBox);
inputPanel.add(row2);
inputPanel.add(okButton);
mainPanel.add(panel);
panel.add(inputPanel);
RootPanel.get().add(mainPanel);
String url = "http://localhost:3000/pages/welcome";
getRequest(url);
}


public void onClick(ClickEvent event)
{
Object source = event.getSource();
if (source == okButton) {
String encData = URL.encode("user_name") + "=" +
URL.encode(firstNameBox.getText()) + "&";
encData += URL.encode("password") + "=" +
URL.encode(lastNameBox.getText()) + "&";

String url = "http://localhost:3000/pages/login";
postRequest(url,encData);
}
}


private void getRequest(String url)
{
final RequestBuilder rb =
new RequestBuilder(RequestBuilder.GET,url);
try {
rb.sendRequest(null, new RequestCallback()
{
public void onError(final Request request,
final Throwable exception)
{
Window.alert(exception.getMessage());
}
public void onResponseReceived(final Request request,
final Response response)
{
textarea.setText(response.getText());
}
});
}
catch (final Exception e) {
Window.alert(e.getMessage());
}
} // end getRequest


private void postRequest(String url, String data)
{
final RequestBuilder rb =
new RequestBuilder(RequestBuilder.POST,url);
rb.setHeader("Content-type",
"application/x-www-form-urlencoded");
try {
rb.sendRequest(data, new RequestCallback()
{
public void onError(final Request request,
final Throwable exception)
{
Window.alert(exception.getMessage());
}
public void onResponseReceived(final Request request,
final Response response)
{
firstNameBox.setText("");
lastNameBox.setText("");

String url1 = "http://localhost:3000/pages/welcome";
getRequest(url1);
}
});
}
catch (final Exception e) {
Window.alert(e.getMessage());
}
} // end postRequst
}
