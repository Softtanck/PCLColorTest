package com.softtanck.tangce.pclcolortest.translator;

import com.softtanck.tangce.pclcolortest.usbprinter.PCLPrinter;
import com.softtanck.tangce.pclcolortest.usbprinter.PSPrinter;

/**
 * Created by diego on 25-09-15.
 */
public interface Translator {
	public void addPCLImage(PCLPrinter printer);

	public void addPSImage(PSPrinter printer);

	public int getPCLSize();

	public int getWidth();

	public int getHeight();
}
