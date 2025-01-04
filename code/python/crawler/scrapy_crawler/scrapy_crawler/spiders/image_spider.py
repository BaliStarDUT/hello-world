import scrapy
import urllib
from scrapy_crawler.items import ScrapyCrawlerItem
import json
import logging


class Image_Spider(scrapy.Spider):
    name = "image_spider"

    def start_requests(self):
            urls = [
                "https://www.cyjdm.com/58524.html",
            ]
            for url in urls:
                yield scrapy.Request(url=url, callback=self.parse)


    def parse(self, response):
        logging.info(response)
        logging.info(response.xpath('//*[@id="content"]/div/p[3]/span/img'))
        # for result in response.xpath('//*[@id="content"]/div/p[3]/span/img'):
        for result in response.css("#content > div > p:nth-child(3) > span > img"):
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