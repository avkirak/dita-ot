package org.dita.dost.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.dita.dost.util.StringUtils;
import org.junit.Test;

public class TestStringUtils {

	@Test
	public void testAssembleString() {
		String result = null;
		ArrayList<String> input = new ArrayList<String>();
		result = StringUtils.assembleString(null, ";");
		assertEquals("", result);
		result = StringUtils.assembleString(new ArrayList<String>(), ";");
		assertEquals("", result);
		input.add("first");
		input.add("second");
		input.add("third");
		result = StringUtils.assembleString(input, ";");
		assertEquals("first;second;third", result);
	}

	@Test
	public void testEscapeXMLString() {
		String result = null;
		String input = "<this is test of char update for xml href=\" see link: http://www.ibm.com/download.php?abc=123&def=456\">'test' </test>";
		String expected = "&lt;this is test of char update for xml href=&quot; see link: http://www.ibm.com/download.php?abc=123&amp;def=456&quot;&gt;&apos;test&apos; &lt;/test&gt;";
		result = StringUtils.escapeXML(input);
		assertEquals(expected, result);
	}

	@Test
	public void testEscapeXMLCharArrayIntInt() {
		String result = null;
		char[] input = "<this is test of char update for xml href=\" see link: http://www.ibm.com/download.php?abc=123&def=456\">'test' </test>".toCharArray();
		String expected = "&lt;this is test of char update for xml href=&quot; see link: http://www.ibm.com/download.php?abc=123&amp;def=456&quot;&gt;&apos;test&apos; &lt;/test&gt;";
		result = StringUtils.escapeXML(input,0,input.length);
		assertEquals(expected, result);
	}

	@Test
	public void testGetEntity() {
		String result = null;
		result = StringUtils.getEntity("abc");
		assertEquals("&abc;", result);
		result = StringUtils.getEntity("%xyz");
		assertEquals("%xyz;", result);
	}

	@Test
	public void testCheckEntity() {
		assertFalse(StringUtils.checkEntity("lt"));
		assertFalse(StringUtils.checkEntity("gt"));
		assertFalse(StringUtils.checkEntity("quot"));
		assertFalse(StringUtils.checkEntity("amp"));
		assertTrue(StringUtils.checkEntity("abc"));
	}

	@Test
	public void testReplaceAll() {
		String result = null;
		result = StringUtils.replaceAll("abababa", "aba", "c");
		assertEquals("cbc", result);
	}

	@Test
	public void testRestoreMap() {
		Map expected = new HashMap();
		expected.put("abc", "def");
		expected.put("ghi", "jkl");
		expected.put("mno", "pqr");
		Map result = StringUtils.restoreMap("abc=def,ghi=jkl,mno=pqr");
		assertEquals(expected, result);
	}

	@Test
	public void testIsEmptyString() {
		assertTrue(StringUtils.isEmptyString(null));
		assertTrue(StringUtils.isEmptyString(""));
		assertTrue(StringUtils.isEmptyString("      "));
		assertFalse(StringUtils.isEmptyString("abc"));
	}

	@Test
	public void testSetOrAppend() {
		String input1 = "input1";
		String input2 = "input2";
		String result = null;
		result = StringUtils.setOrAppend(null, input2, true);
		assertEquals("input2", result);
		result = StringUtils.setOrAppend(input1, null, true);
		assertEquals("input1", result);
		result = StringUtils.setOrAppend(input1, input2, false);
		assertEquals("input1input2", result);
		result = StringUtils.setOrAppend(input1, input2, true);
		assertEquals("input1 input2", result);
		
	}

	@Test
	public void testGetLocale() {
		Locale expected1 = new Locale("zh","cn");
		Locale result1 = StringUtils.getLocale("zh-cn");
		assertEquals(expected1, result1);
		Locale expected2 = new Locale("zh");
		Locale result2 = StringUtils.getLocale("zh_cn");
		assertEquals(expected2, result2);
		Locale expected3 = new Locale("zh","cn","gb2312");
		Locale result3 = StringUtils.getLocale("zh-cn-gb2312");
		assertEquals(expected3, result3);
	}

}
