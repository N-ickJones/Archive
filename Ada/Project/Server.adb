--Author:  Nicholas Jones
--Purpose: To Sustain Client Communication
--Date: 03/29/2019
--Version: 1.0

package body Server is

  Client_Names: array(1..8) of String(1..8);

  task type Add_Client is
      entry Connect(Client_Socket: Socket_Type);
   end Add_Client;

  task body Add_Client is
  begin
    accept Connect(Client_Socket: Socket_Type) do
      loop
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
    			-- End Socket Connection
        end loop;
    end Connect;
  end Add_Client;

  type Task_Pool is array(Integer range 1..2) of Add_Client;
  My_Pool : Task_Pool;
  Client_Num: Integer := 1;

  procedure Start(S: in out aServer) is
    use type Selector_Status;
    Listener_Socket  : Socket_Type;
    Listener_Address : constant Sock_Addr_Type := (
      Family => Family_Inet,
      Addr   => Inet_Addr ("127.0.0.1"),
      Port   => 4000
    );
    Client_Socket   : Socket_Type;
    Client_Socket2  : Socket_Type;
    Client_Address  : Sock_Addr_Type;
    Select_Status : Selector_Status;
    Count: Integer := 1;

  begin
    Create_Socket (Listener_Socket);
    Set_Socket_Option (
      Socket => Listener_Socket,
      Level  => Socket_Level,
      Option => (
        Name    => Reuse_Address,
        Enabled => True
      )
    );
    Bind_Socket (
      Socket  => Listener_Socket,
      Address => Listener_Address
    );
    Listen_Socket (Listener_Socket);
    Put_Line("Listening on " & Image (Listener_Address));

		-- Start Server
    loop
			--1) Accept A Socket
      if Count = 1 then
        Accept_Socket (
          Server   => Listener_Socket,
          Socket   => Client_Socket,
          Address  => Client_Address,
          Timeout  => Forever,
          Selector => S.Accepting_Selector'Access,
          Status   => Select_Status
        );
      elsif Count = 2 then
        Accept_Socket (
          Server   => Listener_Socket,
          Socket   => Client_Socket2,
          Address  => Client_Address,
          Timeout  => Forever,
          Selector => S.Accepting_Selector'Access,
          Status   => Select_Status
        );
      end if;

      if Select_Status = Completed and Count = 1 then
        Put_Line("Client from " & Image(Client_Address));
        -- Start Thread Here
        My_Pool(Client_Num).Connect(Client_Socket);
        Count := Count + 1;

      elsif Select_Status = Completed and Count = 2 then
        Put_Line("Client from " & Image(Client_Address));
        -- Start Thread Here
        My_Pool(Client_Num).Connect(Client_Socket);
        Count := Count + 1;
      end if;

      exit when Select_Status = Aborted;


    end loop;

		-- 	--2(Exit) When procedure Stop is Called
    --   exit when Select_Status = Aborted;
    --
		-- 	--3(If) Socket Connected then
    --   if Select_Status = Completed then
    --     Put_Line("Client from " & Image(Client_Address));
    --     String'Output(Stream(Client_Socket), "You have connected Successfully.");
    --     Integer'Output(Stream(Client_Socket), 31);
    --     String'Output(Stream(Client_Socket), "Enter a Username");
    --     Integer'Output(Stream(Client_Socket), 16);
    --     --Accept Username
    --     declare
    --       Message: String := String'Input(Stream(Client_Socket));
    --       Length: Integer := Integer'Input(Stream(Client_Socket));
    --       Str: String(1..8);
    --     begin
    --       Str(1..Length) := Message;
    --       Client_Names(1) := Str;
    --       String'Output(Stream(Client_Socket), "Username is now " & Message);
    --       Integer'Output(Stream(Client_Socket), 16+Length);
    --     end;
    --     --Begin Loop
    --     loop
		-- 		  declare
		-- 			  Message: String := String'Input(Stream(Client_Socket));
    --         Length: Integer := Integer'Input(Stream(Client_Socket));
    --       begin
		-- 			  put_line(Message & " [" & Client_Names(1) & "]");
    -- 			  String'Output(Stream(Client_Socket), "Message Recieved");
    --         Integer'Output(Stream(Client_Socket), 16);
    --   	  end;
    -- 	  end loop;
		-- 		Close_Socket (Client_Socket);
		-- 		put_line("Client Socket Closed");
    --   end if;
		-- 	-- End Socket Connection
    -- end loop;
		-- -- End Server
    -- Close_Socket (Listener_Socket);
  end Start;

  procedure Stop(S: in out aServer) is
  begin
    Put_Line ("");Put_Line ("Stopping...");
    Abort_Selector(S.Accepting_Selector);
  end Stop;

  overriding procedure Initialize(Object: in out aServer) is
  begin
		Put_Line ("Initializing...");Put_Line ("");
    Create_Selector(Object.Accepting_Selector);
  end Initialize;

  overriding procedure Finalize(Object: in out aServer) is
  begin
		Put_Line ("");Put_Line ("Finalizing...");
    Close_Selector(Object.Accepting_Selector);
  end Finalize;

end Server;
