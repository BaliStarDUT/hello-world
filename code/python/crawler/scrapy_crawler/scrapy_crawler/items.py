# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class ScrapyCrawlerItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    # host= scrapy.Field()
    # alt=scrapy.Field()
    image_urls = scrapy.Field()
    # images = scrapy.Field()
    # pass
