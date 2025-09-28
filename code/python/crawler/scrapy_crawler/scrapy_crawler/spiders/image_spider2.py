'''
user.name: james
Author: james
Date: 2025-05-17 10:31:20
Description: Copyright (c) 2025, All Rights Reserved. 
'''
import scrapy
import urllib
from scrapy_crawler.items import ScrapyCrawlerItem
import json
import logging


class Image_Spider(scrapy.Spider):
    name = "image_spider2"

    def start_requests(self):
            urls = [
                "https://dimtown.com/125615.html",
            ]
            for url in urls:
                yield scrapy.Request(url=url, callback=self.parse)

    def parse(self, response):
        next_selector =response.xpath("//*[@class,'gallery']/img")
# /html/body/div[1]/div[2]/img[1]
    def parse_item(self, response):
        logging.info(response)
        images=response.xpath('//*[@id="content"]/div//img')
        logging.info(images)
        # images = a.css('img')
        # for result in response.xpath('//*[@id="content"]/div/p[3]/span/img'):
        for result in images:
            logging.info(result.attrib)
            item = ScrapyCrawlerItem()
            # item['host']=self.name
            # item['alt']=result.attrib['alt']
            item['image_urls']= [result.attrib['src']]
            logging.info("==%s",item)
            yield item

    @classmethod
    def update_settings(cls, settings):
        super().update_settings(settings)
        settings.set("HTTPERROR_ALLOWED_CODES", "301", priority="spider")