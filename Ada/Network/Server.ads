--Author:  Nicholas Jones
--Purpose: To Sustain Client Communication
--Date: 03/29/2019
--Version: 1.0

with Ada.Finalization;
with GNAT.Sockets; use GNAT.Sockets;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Io_Exceptions;
with Ada.Streams;use type Ada.Streams.Stream_Element_Count;

package Server is
  type aServer is new Ada.Finalization.Limited_Controlled with private;
  procedure Start(S: in out aServer);
  procedure Stop(S: in out aServer);

private
  type aServer is new Ada.Finalization.Limited_Controlled with record
    Accepting_Selector : aliased Gnat.Sockets.Selector_Type;
  end record;
  overriding procedure Initialize(Object: in out aServer);
  overriding procedure Finalize(Object: in out aServer);
end Server;

--Selector_Type, Abort_Selector, Create_Selector, Close_Selector, Selector_Status,
-- Socket_Type, Sock_Addr_Type, Family_Inet, Inet_Addr, Create_Socket, Set_Socket_Option,
-- Reuse_Address, Bind_Socket, Listen_Socket, Image, Accept_Socket, Forever, Aborted
