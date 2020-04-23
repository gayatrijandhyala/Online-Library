import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class BuyBooksV1 extends Frame 
{
	Button BuyBB;
	TextField buidTf, boidTf;
	//TextArea errorText;
	Connection connection;
	Statement statement;
	public BuyBooksV1() 
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) 
		{
			System.err.println("Cannot find and load driver");
			System.exit(1);
		}
		connectToDB();
	}

	public void connectToDB() 
    {
		try 
		{
		  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gayatri","manager");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
    }
	public void buildGUI() 
	{		
		BuyBB = new Button("Buy Book");
		BuyBB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{			  
				  String query= "INSERT INTO buys VALUES(" + buidTf.getText() + ", " + boidTf.getText() + ")";
				  int i = statement.executeUpdate(query);
				 // errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
			//	  displaySQLErrors(insertException);
				}
			}
		});

		buidTf = new TextField(15);
		boidTf = new TextField(15);
		
		
		//errorText = new TextArea(10, 40);
		//errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Buyer ID:"));
		first.add(buidTf);
		first.add(new Label("Book ID:"));
		first.add(boidTf);
		
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(BuyBB);
        second.setBounds(125,220,150,100);         
				
		setLayout(null);

		add(first);
		add(second);
	    
		setTitle("Buying Books");
		setSize(500, 600);
		setVisible(true);
	}
	

	public static void main(String[] args) 
	{
		BuyBooksV1 bb = new BuyBooksV1();

		bb.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			System.exit(0);
		  }
		});
		
		bb.buildGUI();
	}
}
