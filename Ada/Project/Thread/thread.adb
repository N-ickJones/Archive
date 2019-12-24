with Ada.Exceptions; use Ada.Exceptions;
with Ada.Text_Io; use Ada.Text_Io;
with Ada.Integer_Text_Io; use Ada.Integer_Text_IO;

procedure thread is

	protected type Semaphore(K : Integer := 0) is
		entry Get;
   	procedure Add;
	private
   	Count : Integer := K;
	end Semaphore;

	protected body Semaphore is
   	entry Get when Count > 0 is
   	begin
      put("Recieving Message");new_line;
			Count := Count - 1;
   	end Get;

		procedure Add is
  	begin
    	put("Sending Message");new_line;
			Count := Count + 1;
   	end Add;

	end Semaphore;

	 Client : Semaphore;

begin
	loop
  	select
    	Client.Get;
  	or delay 0.5;
     	put("No Data in Buffer!");new_line;
  	end select;
	end loop;
end thread;
