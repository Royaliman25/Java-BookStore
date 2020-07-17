

@RunWith(PowerMockRunner.class)
@PrepareForTest(ControllerServlet.class)
public class Module1_Task2_and_3_IT {

  private ControllerServlet controllerServlet;
  private Method method = null;

  @Before
  public void setUp() throws Exception {
    try {
      method = Whitebox.getMethod(ControllerServlet.class,
                "deleteBook", HttpServletRequest.class, HttpServletResponse.class);
    } catch (Exception e) {}
}
