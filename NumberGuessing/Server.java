import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		Random rand = new Random();
		int pcTahmin = rand.nextInt(100) + 1; // 1-100 arası random sayı üretme
		String strFromClient;
		int intFromClient;
		int tries = 3;
		boolean bool = true;
		try
		{
			// Server 6013 portundan Client'ı dinliyor
			serverSocket = new ServerSocket(6013);
		}
		catch (IOException ioe)
		{
			System.err.println(ioe);
		}

		System.out.println(pcTahmin);

		while (bool)
		{
			clientSocket = serverSocket.accept();

			// Client'a veri gönderme
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			// Client'dan gelen verileri tutma
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			while (bool && (strFromClient = in.readLine()) != null)
			{
				intFromClient = Integer.valueOf(strFromClient); // Gelen veriyi Integer'a çevir
				if (intFromClient > pcTahmin)
				{
					tries--;
					if (tries == 0)
					{
						out.println("Lose! Press Enter to exit...");
						clientSocket.close();
						serverSocket.close();
						bool = false;
					}
					else
					{
						out.println("Decrease! Tries left: " + tries);
					}
				}
				else if (intFromClient < pcTahmin)
				{
					tries--;
					if (tries == 0)
					{
						out.println("Lose! Press Enter to exit...");
						clientSocket.close();
						serverSocket.close();
						bool = false;
					}
					else
					{
						out.println("Increase! Tries left: " + tries);
					}
				}
				else if (intFromClient == pcTahmin)
				{
					out.println("Win! Press Enter to exit...");
					clientSocket.close();
					serverSocket.close();
					bool = false;
				}
			}
		}
	}
}