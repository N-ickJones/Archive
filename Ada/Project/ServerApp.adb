--Author:  Nicholas Jones
--Purpose: To Sustain Client Communication
--Date: 03/29/2019
--Version: 1.0

with Ada.Interrupts;
with Ada.Interrupts.Names;

with Server;

package body ServerApp is

  pragma Interrupt_State (
    Name  => Ada.Interrupts.Names.SIGINT,
    State => USER
  );

  ThisServer : Server.aServer;

  protected Interrupt_Handler is
    procedure Handle
      with Interrupt_Handler;
  end Interrupt_Handler;

	protected body Interrupt_Handler is
    procedure Handle is begin
      Server.Stop(ThisServer);
    end Handle;
  end Interrupt_Handler;

  procedure Run is
  begin
    Ada.Interrupts.Attach_Handler (
      Interrupt_Handler.Handle'Access,
      Ada.Interrupts.Names.SIGINT
    );
    Server.Start(ThisServer);
  end Run;

end ServerApp;
