import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	public static void main(String[] args) throws IOException
	{
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String userGuess;
		boolean bool1 = true;
		try
		{
			socket = new Socket("127.0.0.1", 6013); // IP adresi ve portu
		}
		catch (IOException ioe)
		{
			System.err.println(ioe);
		}

		// Server'a veri gönderme
		out = new PrintWriter(socket.getOutputStream(), true);

		// Server'dan gelen verileri alma
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		System.out.println("I chose a number between 1-100");
		System.out.println("Please enter your guess: ");

		// Server'a gönderilecek sayı
		BufferedReader data = new BufferedReader(new InputStreamReader(System.in));

		while (bool1 && !socket.isClosed() && !(userGuess = data.readLine()).isEmpty())
		{
			out.println(userGuess); // Tahmini Server'a gönder
			System.out.println(in.readLine()); // Server'dan gelen cevabı yazdır
		}
		socket.close();
	}
}