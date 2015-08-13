package com.koltem.jccverilogparser ;

public class MyToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public MyToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  int realKind = JccVerilogParserConstants.STUFF_TO_IGNORE;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new MyToken(ofKind, tokenImage);
  }
}
