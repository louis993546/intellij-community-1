// "Remove '@exception'" "true"

class Foo {
  /**
   * @param someInt blah-blah-blah
   * @return blah-blah-blah
   * @exception<caret>
   */
  double foo(int someInt) {
    return 3.14;
  }
}
