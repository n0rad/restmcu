package org.housecream.restmcu.it;

import org.housecream.restmcu.api.domain.line.RestMcuLineSettings;
import org.junit.Rule;
import org.junit.Test;
import fr.norad.core.lang.exception.NotFoundException;

public class LineResourceUnknownIT {

    @Rule
    public RestmcuTestRule restmcu = new RestmcuTestRule();

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line() throws Exception {
        restmcu.getLineResource().getLine(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line2() throws Exception {
        restmcu.getLineResource().getLine(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line3() throws Exception {
        restmcu.getLineResource().getLine(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line4() throws Exception {
        restmcu.getLineResource().getLine(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line5() throws Exception {
        restmcu.getLineResource().getLineValue(42);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line6() throws Exception {
        restmcu.getLineResource().getLineValue(-1);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line13() throws Exception {
        restmcu.getLineResource().setLineSettings(42, new RestMcuLineSettings());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line14() throws Exception {
        restmcu.getLineResource().setLineSettings(-1, new RestMcuLineSettings());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line15() throws Exception {
        restmcu.getLineResource().setLineValue(42, 0f);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_on_unknown_line16() throws Exception {
        restmcu.getLineResource().setLineValue(-1, 0f);
    }

}
