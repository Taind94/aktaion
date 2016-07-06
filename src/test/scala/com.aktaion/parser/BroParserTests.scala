/**
  * Copyright 2016 - Caspida Inc., All rights reserved.
  * This is Caspida proprietary and confidential material and its use
  * is subject to license terms.
  * Created on 7/1/16.
  *
  * @author joez
  */

package com.aktaion.parser

import com.aktaion.common.SimpleTestTools

class BroParserTests extends SimpleTestTools {

  test("Basic Bro HTTP Parser") {
    val myHttpParser = new BroHttpParser
    val rawLogLine = """1407536946.250769	CuXBEZ1N3EwxNX5Frl	192.168.57.105	44688	192.168.57.110	80	1	POST	citadel.com	/file.php	-	Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C)	122	5280	200	OK	-	-	-	(empty)	-	-	-	FZrQg1NzTvumR5iG	-	FsWWXv2NaAihisYjY	-"""
    val parsedData = myHttpParser.tokenizeData(rawLogLine)

    val output = parsedData.get

    output.host shouldBe "citadel.com"
    output.id_orig_host shouldBe "192.168.57.105"




    println(output)
  }

  test("Bro HTTP File"){
    val myHttpParser = new BroHttpParser
    val file: String = getFileStringFromResourcePath("/parser.bro/citadelsample/http.log")
    val lines: Array[String] = getLinesFromFile(file)
    val parsed: Array[BroHttpLogEvent] = lines.flatMap(singleLine => myHttpParser.tokenizeData(singleLine))

    parsed.foreach(println)
  }


}
