with Ada.Exceptions; use Ada.Exceptions;
with Ada.Text_Io; use Ada.Text_Io;
with Ada.Integer_Text_Io; use Ada.Integer_Text_IO;
with GNAT.Sockets; use GNAT.Sockets;
with Ada.Finalization;

procedure thread2 is

	type aServer is new Ada.Finalization.Limited_Controlled with record
    Accepting_Selector : aliased Gnat.Sockets.Selector_Type;
  end record;

	S: aServer;
	Client_Names: array(1..8) of String(1..8);

	Client_Socket: Socket_Type;
	Server_Socket: Socket_Type;
	Client_Addr: Sock_Addr_Type;
	Select_Stat: Selector_Status;

	task type Add_Client is
      entry Connect(aClient: Socket_Type; Client_Address: in out Sock_Addr_Type;
										Listener_Socket: Socket_Type;
										Select_Status : in out Selector_Status; S: in out aServer);
      entry Disconnect;
   end Add_Client;

   task body Add_Client is
      Local_Num : Integer;
   begin
      accept Connect(aClient: Socket_Type; Client_Address: in out Sock_Addr_Type;
			 								Listener_Socket: Socket_Type;
											Select_Status : in out Selector_Status; S: in out aServer) do

				loop
					--1) Accept A Socket
		      Accept_Socket (
		        Server   => Listener_Socket,
		        Socket   => Client_Socket,
		        Address  => Client_Address,
		        Timeout  => Forever,
		        Selector => S.Accepting_Selector'Access,
		        Status   => Select_Status
		      );

					--2(Exit) When procedure Stop is Called
		      exit when Select_Status = Aborted;

					--3(If) Socket Connected then
		      if Select_Status = Completed then
		        Put_Line("Client from " & Image(Client_Address));
		        String'Output(Stream(Client_Socket), "You have connected Successfully.");
		        Integer'Output(Stream(Client_Socket), 31);
		        String'Output(Stream(Client_Socket), "Enter a Username");
		        Integer'Output(Stream(Client_Socket), 16);
		        --Accept Username
		        declare
		          Message: String := String'Input(Stream(Client_Socket));
		          Length: Integer := Integer'Input(Stream(Client_Socket));
		          Str: String(1..8);
		        begin
		          Str(1..Length) := Message;
		          Client_Names(1) := Str;
		          String'Output(Stream(Client_Socket), "Username is now " & Message);
		          Integer'Output(Stream(Client_Socket), 16+Length);
		        end;
		        --Begin Loop
		        loop
						  declare
							  Message: String := String'Input(Stream(Client_Socket));
		            Length: Integer := Integer'Input(Stream(Client_Socket));
		          begin
							  put_line(Message & " [" & Client_Names(1) & "]");
		    			  String'Output(Stream(Client_Socket), "Message Recieved");
		            Integer'Output(Stream(Client_Socket), 16);
		      	  end;
		    	  end loop;
						Close_Socket (Client_Socket);
						put_line("Client Socket Closed");
		      end if;
					-- End Socket Connection
		    end loop;
		    Close_Socket (Listener_Socket);
      end Connect;

      accept Disconnect do
        put("d");new_line;
      end Disconnect;
   end Add_Client;

   type Task_Pool is array(Positive range 1..10) of Add_Client;
   My_Pool : Task_Pool;
   The_Value : Integer;
begin



   for num in My_Pool'Range loop
      My_Pool(num).Connect(Client_Socket, Client_Addr, Server_Socket, Select_Stat, S);
   end loop;

   for num in My_Pool'Range loop
      My_Pool(num).Disconnect;
      --Put_Line("Task" & Integer'Image(num) & " reports"
      --   & Integer'Image(The_Value));
   end loop;

end thread2;
