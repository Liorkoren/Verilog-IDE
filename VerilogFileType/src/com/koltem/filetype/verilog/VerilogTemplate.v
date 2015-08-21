/**
 * Verilog 1995 Module Template.
 * @author Lior Koren
 */
module (clk, reset, din, dout );
  // Paramters
  parameter WIDTH = 8 ;

  // Ports
  input clk;
  input reset;
  input [WIDTH-1:0] din;
  output reg [WIDTH-1:0] dout;

  // Nets
  wire [WIDTH-1:0] dtmp;

  // Continuos assignments
  assign dtmp = ~din;

  // Procedural logic
  always @(posedge clk or negedge reset) begin
    dout <= dtmp;
  end
endmodule
