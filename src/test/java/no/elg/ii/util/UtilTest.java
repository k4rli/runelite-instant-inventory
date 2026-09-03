/*
 * Copyright (c) 2023-2025 Elg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package no.elg.ii.util;

import net.runelite.api.widgets.Widget;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilTest {

  private Widget widgetWithQuantity(int quantity) {
    Widget widget = mock(Widget.class);
    when(widget.getItemQuantity()).thenReturn(quantity);
    return widget;
  }

  @Test
  public void allButOneWithQuantityOneReturnsNoMenuOptionNumber() {
    assertEquals(Util.NO_MENU_OPTION_NUMBER, Util.getNumberFromMenuOption("Withdraw-All-but-1", widgetWithQuantity(1)));
  }

  @Test
  public void allButOneWithQuantityZeroReturnsNoMenuOptionNumber() {
    assertEquals(Util.NO_MENU_OPTION_NUMBER, Util.getNumberFromMenuOption("Withdraw-All-but-1", widgetWithQuantity(0)));
  }

  @Test
  public void allButOneWithQuantityTwoReturnsOne() {
    assertEquals(1, Util.getNumberFromMenuOption("Withdraw-All-but-1", widgetWithQuantity(2)));
  }

  @Test
  public void allReturnsMaxValue() {
    assertEquals(Integer.MAX_VALUE, Util.getNumberFromMenuOption("Withdraw-All", widgetWithQuantity(1)));
  }

  @Test
  public void plainNumberIsParsed() {
    assertEquals(5, Util.getNumberFromMenuOption("Withdraw-5", widgetWithQuantity(1)));
  }

  @Test
  public void garbageIsNoMenuOptionNumber() {
    assertEquals(Util.NO_MENU_OPTION_NUMBER, Util.getNumberFromMenuOption("Withdraw-abc", widgetWithQuantity(1)));
  }

  @Test
  public void missingHyphenIsNoMenuOptionNumber() {
    assertEquals(Util.NO_MENU_OPTION_NUMBER, Util.getNumberFromMenuOption("Withdraw", widgetWithQuantity(1)));
  }
}
