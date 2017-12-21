PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "migrations" ("id" integer not null primary key autoincrement, "name" varchar(255), "version" varchar(255), "currentVersion" varchar(255));
INSERT INTO "migrations" VALUES(1,'1-create-tables.js','init','1.6');
INSERT INTO "migrations" VALUES(2,'2-create-fixtures.js','init','1.6');
INSERT INTO "migrations" VALUES(3,'1-post-excerpt.js','1.3','1.6');
INSERT INTO "migrations" VALUES(4,'1-codeinjection-post.js','1.4','1.6');
INSERT INTO "migrations" VALUES(5,'1-og-twitter-post.js','1.5','1.6');
CREATE TABLE "posts" ("id" varchar(24) not null, "uuid" varchar(36) not null, "title" varchar(2000) not null, "slug" varchar(191) not null, "mobiledoc" text null, "html" text null, "amp" text null, "plaintext" text null, "feature_image" varchar(2000) null, "featured" boolean not null default '0', "page" boolean not null default '0', "status" varchar(50) not null default 'draft', "locale" varchar(6) null, "visibility" varchar(50) not null default 'public', "meta_title" varchar(2000) null, "meta_description" varchar(2000) null, "author_id" varchar(24) not null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, "published_at" datetime null, "published_by" varchar(24) null, "custom_excerpt" varchar(2000) null, "codeinjection_head" text null, "codeinjection_foot" text null, "og_image" varchar(2000) null, "og_title" varchar(300) null, "og_description" varchar(500) null, "twitter_image" varchar(2000) null, "twitter_title" varchar(300) null, "twitter_description" varchar(500) null, primary key ("id"));
INSERT INTO "posts" VALUES('599bed2b4c318d0030c6c647','c9f75d06-bbea-4b6b-97c3-7c519c542af1','Setting up your own Ghost theme','themes','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Creating a totally custom design for your publication\n\nGhost comes with a beautiful default theme called Casper, which is designed to be a clean, readable publication layout and can be easily adapted for most purposes. However, Ghost can also be completely themed to suit your needs. Rather than just giving you a few basic settings which act as a poor proxy for code, we just let you write code.\n\nThere are a huge range of both free and premium pre-built themes which you can get from the [Ghost Theme Marketplace](http://marketplace.ghost.org), or you can simply create your own from scratch.\n\n[![marketplace](https://casper.ghost.org/v1.0.0/images/marketplace.jpg)](http://marketplace.ghost.org)\n\n> Anyone can write a completely custom Ghost theme, with just some solid knowledge of HTML and CSS\n\nGhost themes are written with a templating language called handlebars, which has a bunch of dynamic helpers to insert your data into template files. Like `{{author.name}}`, for example, outputs the name of the current author.\n\nThe best way to learn how to write your own Ghost theme is to have a look at [the source code for Casper](https://github.com/TryGhost/Casper), which is heavily commented and should give you a sense of how everything fits together.\n\n- `default.hbs` is the main template file, all contexts will load inside this file unless specifically told to use a different template.\n- `post.hbs` is the file used in the context of viewing a post.\n- `index.hbs` is the file used in the context of viewing the home page.\n- and so on\n\nWe''ve got [full and extensive theme documentation](http://themes.ghost.org/docs/about) which outlines every template file, context and helper that you can use.\n\nIf you want to chat with other people making Ghost themes to get any advice or help, there''s also a **#themes** channel in our [public Slack community](https://slack.ghost.org) which we always recommend joining!"}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Creating a totally custom design for your publication</p>
<p>Ghost comes with a beautiful default theme called Casper, which is designed to be a clean, readable publication layout and can be easily adapted for most purposes. However, Ghost can also be completely themed to suit your needs. Rather than just giving you a few basic settings which act as a poor proxy for code, we just let you write code.</p>
<p>There are a huge range of both free and premium pre-built themes which you can get from the <a href="http://marketplace.ghost.org">Ghost Theme Marketplace</a>, or you can simply create your own from scratch.</p>
<p><a href="http://marketplace.ghost.org"><img src="https://casper.ghost.org/v1.0.0/images/marketplace.jpg" alt="marketplace"></a></p>
<blockquote>
<p>Anyone can write a completely custom Ghost theme, with just some solid knowledge of HTML and CSS</p>
</blockquote>
<p>Ghost themes are written with a templating language called handlebars, which has a bunch of dynamic helpers to insert your data into template files. Like <code>{{author.name}}</code>, for example, outputs the name of the current author.</p>
<p>The best way to learn how to write your own Ghost theme is to have a look at <a href="https://github.com/TryGhost/Casper">the source code for Casper</a>, which is heavily commented and should give you a sense of how everything fits together.</p>
<ul>
<li><code>default.hbs</code> is the main template file, all contexts will load inside this file unless specifically told to use a different template.</li>
<li><code>post.hbs</code> is the file used in the context of viewing a post.</li>
<li><code>index.hbs</code> is the file used in the context of viewing the home page.</li>
<li>and so on</li>
</ul>
<p>We''ve got <a href="http://themes.ghost.org/docs/about">full and extensive theme documentation</a> which outlines every template file, context and helper that you can use.</p>
<p>If you want to chat with other people making Ghost themes to get any advice or help, there''s also a <strong>#themes</strong> channel in our <a href="https://slack.ghost.org">public Slack community</a> which we always recommend joining!</p>
</div>',NULL,'Creating a totally custom design for your publication

Ghost comes with a beautiful default theme called Casper, which is designed to
be a clean, readable publication layout and can be easily adapted for most
purposes. However, Ghost can also be completely themed to suit your needs.
Rather than just giving you a few basic settings which act as a poor proxy for
code, we just let you write code.

There are a huge range of both free and premium pre-built themes which you can
get from the Ghost Theme Marketplace [http://marketplace.ghost.org], or you can
simply create your own from scratch.

  [http://marketplace.ghost.org]

Anyone can write a completely custom Ghost theme, with just some solid knowledge
of HTML and CSS

Ghost themes are written with a templating language called handlebars, which has
a bunch of dynamic helpers to insert your data into template files. Like 
{{author.name}}, for example, outputs the name of the current author.

The best way to learn how to write your own Ghost theme is to have a look at 
the
source code for Casper [https://github.com/TryGhost/Casper], which is heavily
commented and should give you a sense of how everything fits together.

 * default.hbs  is the main template file, all contexts will load inside this
   file unless specifically told to use a different template.
 * post.hbs  is the file used in the context of viewing a post.
 * index.hbs  is the file used in the context of viewing the home page.
 * and so on

We''ve got full and extensive theme documentation
[http://themes.ghost.org/docs/about]  which outlines every template file,
context and helper that you can use.

If you want to chat with other people making Ghost themes to get any advice or
help, there''s also a #themes  channel in our public Slack community
[https://slack.ghost.org]  which we always recommend joining!','https://casper.ghost.org/v1.0.0/images/design.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:36:59','5951f5fca366002ebd5dbef7','2017-08-22 08:36:59','1','2017-08-22 08:36:59','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2b4c318d0030c6c648','75d58b97-4d36-4ad8-82b2-b742cae4d127','Advanced Markdown tips','advanced-markdown','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"There are lots of powerful things you can do with the Ghost editor\n\nIf you''ve gotten pretty comfortable with [all the basics](/the-editor/) of writing in Ghost, then you may enjoy some more advanced tips about the types of things you can do with Markdown!\n\nAs with the last post about the editor, you''ll want to be actually editing this post as you read it so that you can see all the Markdown code we''re using.\n\n\n## Special formatting\n\nAs well as bold and italics, you can also use some other special formatting in Markdown when the need arises, for example:\n\n+ ~~strike through~~\n+ ==highlight==\n+ \\*escaped characters\\*\n\n\n## Writing code blocks\n\nThere are two types of code elements which can be inserted in Markdown, the first is inline, and the other is block. Inline code is formatted by wrapping any word or words in back-ticks, `like this`. Larger snippets of code can be displayed across multiple lines using triple back ticks:\n\n```\n.my-link {\n    text-decoration: underline;\n}\n```\n\nIf you want to get really fancy, you can even add syntax highlighting using [Prism.js](http://prismjs.com/).\n\n\n## Full bleed images\n\nOne neat trick which you can use in Markdown to distinguish between different types of images is to add a `#hash` value to the end of the source URL, and then target images containing the hash with special styling. For example:\n\n![walking](https://casper.ghost.org/v1.0.0/images/walking.jpg#full)\n\nwhich is styled with...\n\n```\nimg[src$=\"#full\"] {\n    max-width: 100vw;\n}\n```\n\nThis creates full-bleed images in the Casper theme, which stretch beyond their usual boundaries right up to the edge of the window. Every theme handles these types of things slightly differently, but it''s a great trick to play with if you want to have a variety of image sizes and styles.\n\n\n## Reference lists\n\n**The quick brown [fox][1], jumped over the lazy [dog][2].**\n\n[1]: https://en.wikipedia.org/wiki/Fox \"Wikipedia: Fox\"\n[2]: https://en.wikipedia.org/wiki/Dog \"Wikipedia: Dog\"\n\nAnother way to insert links in markdown is using reference lists. You might want to use this style of linking to cite reference material in a Wikipedia-style. All of the links are listed at the end of the document, so you can maintain full separation between content and its source or reference.\n\n\n## Creating footnotes\n\nThe quick brown fox[^1] jumped over the lazy dog[^2].\n\n[^1]: Foxes are red\n[^2]: Dogs are usually not red\n\nFootnotes are a great way to add additional contextual details when appropriate. Ghost will automatically add footnote content to the very end of your post.\n\n\n## Full HTML\n\nPerhaps the best part of Markdown is that you''re never limited to just Markdown. You can write HTML directly in the Ghost editor and it will just work as HTML usually does. No limits! Here''s a standard YouTube embed code as an example:\n\n<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Cniqsc9QfDo?rel=0&amp;showinfo=0\" frameborder=\"0\" allowfullscreen></iframe>\n"}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>There are lots of powerful things you can do with the Ghost editor</p>
<p>If you''ve gotten pretty comfortable with <a href="/the-editor/">all the basics</a> of writing in Ghost, then you may enjoy some more advanced tips about the types of things you can do with Markdown!</p>
<p>As with the last post about the editor, you''ll want to be actually editing this post as you read it so that you can see all the Markdown code we''re using.</p>
<h2 id="specialformatting">Special formatting</h2>
<p>As well as bold and italics, you can also use some other special formatting in Markdown when the need arises, for example:</p>
<ul>
<li><s>strike through</s></li>
<li><mark>highlight</mark></li>
<li>*escaped characters*</li>
</ul>
<h2 id="writingcodeblocks">Writing code blocks</h2>
<p>There are two types of code elements which can be inserted in Markdown, the first is inline, and the other is block. Inline code is formatted by wrapping any word or words in back-ticks, <code>like this</code>. Larger snippets of code can be displayed across multiple lines using triple back ticks:</p>
<pre><code>.my-link {
    text-decoration: underline;
}
</code></pre>
<p>If you want to get really fancy, you can even add syntax highlighting using <a href="http://prismjs.com/">Prism.js</a>.</p>
<h2 id="fullbleedimages">Full bleed images</h2>
<p>One neat trick which you can use in Markdown to distinguish between different types of images is to add a <code>#hash</code> value to the end of the source URL, and then target images containing the hash with special styling. For example:</p>
<p><img src="https://casper.ghost.org/v1.0.0/images/walking.jpg#full" alt="walking"></p>
<p>which is styled with...</p>
<pre><code>img[src$=&quot;#full&quot;] {
    max-width: 100vw;
}
</code></pre>
<p>This creates full-bleed images in the Casper theme, which stretch beyond their usual boundaries right up to the edge of the window. Every theme handles these types of things slightly differently, but it''s a great trick to play with if you want to have a variety of image sizes and styles.</p>
<h2 id="referencelists">Reference lists</h2>
<p><strong>The quick brown <a href="https://en.wikipedia.org/wiki/Fox" title="Wikipedia: Fox">fox</a>, jumped over the lazy <a href="https://en.wikipedia.org/wiki/Dog" title="Wikipedia: Dog">dog</a>.</strong></p>
<p>Another way to insert links in markdown is using reference lists. You might want to use this style of linking to cite reference material in a Wikipedia-style. All of the links are listed at the end of the document, so you can maintain full separation between content and its source or reference.</p>
<h2 id="creatingfootnotes">Creating footnotes</h2>
<p>The quick brown fox<sup class="footnote-ref"><a href="#fn1" id="fnref1">[1]</a></sup> jumped over the lazy dog<sup class="footnote-ref"><a href="#fn2" id="fnref2">[2]</a></sup>.</p>
<p>Footnotes are a great way to add additional contextual details when appropriate. Ghost will automatically add footnote content to the very end of your post.</p>
<h2 id="fullhtml">Full HTML</h2>
<p>Perhaps the best part of Markdown is that you''re never limited to just Markdown. You can write HTML directly in the Ghost editor and it will just work as HTML usually does. No limits! Here''s a standard YouTube embed code as an example:</p>
<iframe width="560" height="315" src="https://www.youtube.com/embed/Cniqsc9QfDo?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>
<hr class="footnotes-sep">
<section class="footnotes">
<ol class="footnotes-list">
<li id="fn1" class="footnote-item"><p>Foxes are red <a href="#fnref1" class="footnote-backref">↩︎</a></p>
</li>
<li id="fn2" class="footnote-item"><p>Dogs are usually not red <a href="#fnref2" class="footnote-backref">↩︎</a></p>
</li>
</ol>
</section>
</div>',NULL,'There are lots of powerful things you can do with the Ghost editor

If you''ve gotten pretty comfortable with all the basics [/the-editor/]  of
writing in Ghost, then you may enjoy some more advanced tips about the types of
things you can do with Markdown!

As with the last post about the editor, you''ll want to be actually editing this
post as you read it so that you can see all the Markdown code we''re using.

Special formatting
As well as bold and italics, you can also use some other special formatting in
Markdown when the need arises, for example:

 * strike through
 * highlight
 * *escaped characters*

Writing code blocks
There are two types of code elements which can be inserted in Markdown, the
first is inline, and the other is block. Inline code is formatted by wrapping
any word or words in back-ticks, like this. Larger snippets of code can be
displayed across multiple lines using triple back ticks:

.my-link {
    text-decoration: underline;
}


If you want to get really fancy, you can even add syntax highlighting using 
Prism.js [http://prismjs.com/].

Full bleed images
One neat trick which you can use in Markdown to distinguish between different
types of images is to add a #hash  value to the end of the source URL, and then
target images containing the hash with special styling. For example:



which is styled with...

img[src$="#full"] {
    max-width: 100vw;
}


This creates full-bleed images in the Casper theme, which stretch beyond their
usual boundaries right up to the edge of the window. Every theme handles these
types of things slightly differently, but it''s a great trick to play with if you
want to have a variety of image sizes and styles.

Reference lists
The quick brown fox [https://en.wikipedia.org/wiki/Fox], jumped over the lazy 
dog [https://en.wikipedia.org/wiki/Dog].

Another way to insert links in markdown is using reference lists. You might want
to use this style of linking to cite reference material in a Wikipedia-style.
All of the links are listed at the end of the document, so you can maintain full
separation between content and its source or reference.

Creating footnotes
The quick brown fox[1]  jumped over the lazy dog[2].

Footnotes are a great way to add additional contextual details when appropriate.
Ghost will automatically add footnote content to the very end of your post.

Full HTML
Perhaps the best part of Markdown is that you''re never limited to just Markdown.
You can write HTML directly in the Ghost editor and it will just work as HTML
usually does. No limits! Here''s a standard YouTube embed code as an example:


--------------------------------------------------------------------------------

 1. Foxes are red ↩︎
    
    
 2. Dogs are usually not red ↩︎','https://casper.ghost.org/v1.0.0/images/advanced.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:36:59','5951f5fca366002ebd5dbef7','2017-08-22 08:36:59','1','2017-08-22 08:37:00','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2c4c318d0030c6c649','cbb25e4e-e558-4be9-860e-e8a8065ac6ad','Making your site private','private-sites','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Sometimes you might want to put your site behind closed doors\n\nIf you''ve got a publication that you don''t want the world to see yet because it''s not ready to launch, you can hide your Ghost site behind a simple shared pass-phrase.\n\nYou can toggle this preference on at the bottom of Ghost''s General Settings\n\n![private](https://casper.ghost.org/v1.0.0/images/private.png)\n\nGhost will give you a short, randomly generated pass-phrase which you can share with anyone who needs access to the site while you''re working on it. While this setting is enabled, all search engine optimisation features will be switched off to help keep the site off the radar.\n\nDo remember though, this is *not* secure authentication. You shouldn''t rely on this feature for protecting important private data. It''s just a simple, shared pass-phrase for very basic privacy."}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Sometimes you might want to put your site behind closed doors</p>
<p>If you''ve got a publication that you don''t want the world to see yet because it''s not ready to launch, you can hide your Ghost site behind a simple shared pass-phrase.</p>
<p>You can toggle this preference on at the bottom of Ghost''s General Settings</p>
<p><img src="https://casper.ghost.org/v1.0.0/images/private.png" alt="private"></p>
<p>Ghost will give you a short, randomly generated pass-phrase which you can share with anyone who needs access to the site while you''re working on it. While this setting is enabled, all search engine optimisation features will be switched off to help keep the site off the radar.</p>
<p>Do remember though, this is <em>not</em> secure authentication. You shouldn''t rely on this feature for protecting important private data. It''s just a simple, shared pass-phrase for very basic privacy.</p>
</div>',NULL,'Sometimes you might want to put your site behind closed doors

If you''ve got a publication that you don''t want the world to see yet because
it''s not ready to launch, you can hide your Ghost site behind a simple shared
pass-phrase.

You can toggle this preference on at the bottom of Ghost''s General Settings



Ghost will give you a short, randomly generated pass-phrase which you can share
with anyone who needs access to the site while you''re working on it. While this
setting is enabled, all search engine optimisation features will be switched off
to help keep the site off the radar.

Do remember though, this is not  secure authentication. You shouldn''t rely on
this feature for protecting important private data. It''s just a simple, shared
pass-phrase for very basic privacy.','https://casper.ghost.org/v1.0.0/images/locked.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','1','2017-08-22 08:37:01','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2c4c318d0030c6c64a','ec6b90d4-a8f3-44b0-8120-7df40eea51df','Managing Ghost users','managing-users','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Ghost has a number of different user roles for your team\n\n\n### Authors\n\nThe base user level in Ghost is an author. Authors can write posts, edit their own posts, and publish their own posts. Authors are **trusted** users. If you don''t trust users to be allowed to publish their own posts, you shouldn''t invite them to Ghost admin.\n\n\n### Editors\n\nEditors are the 2nd user level in Ghost. Editors can do everything that an Author can do, but they can also edit and publish the posts of others - as well as their own. Editors can also invite new authors to the site.\n\n\n### Administrators\n\nThe top user level in Ghost is Administrator. Again, administrators can do everything that Authors and Editors can do, but they can also edit all site settings and data, not just content. Additionally, administrators have full access to invite, manage or remove any other user of the site.\n\n\n### The Owner\n\nThere is only ever one owner of a Ghost site. The owner is a special user which has all the same permissions as an Administrator, but with two exceptions: The Owner can never be deleted. And in some circumstances the owner will have access to additional special settings if applicable — for example, billing details, if using Ghost(Pro).\n\n---\n\nIt''s a good idea to ask all of your users to fill out their user profiles, including bio and social links. These will populate rich structured data for posts and generally create more opportunities for themes to fully populate their design. "}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Ghost has a number of different user roles for your team</p>
<h3 id="authors">Authors</h3>
<p>The base user level in Ghost is an author. Authors can write posts, edit their own posts, and publish their own posts. Authors are <strong>trusted</strong> users. If you don''t trust users to be allowed to publish their own posts, you shouldn''t invite them to Ghost admin.</p>
<h3 id="editors">Editors</h3>
<p>Editors are the 2nd user level in Ghost. Editors can do everything that an Author can do, but they can also edit and publish the posts of others - as well as their own. Editors can also invite new authors to the site.</p>
<h3 id="administrators">Administrators</h3>
<p>The top user level in Ghost is Administrator. Again, administrators can do everything that Authors and Editors can do, but they can also edit all site settings and data, not just content. Additionally, administrators have full access to invite, manage or remove any other user of the site.</p>
<h3 id="theowner">The Owner</h3>
<p>There is only ever one owner of a Ghost site. The owner is a special user which has all the same permissions as an Administrator, but with two exceptions: The Owner can never be deleted. And in some circumstances the owner will have access to additional special settings if applicable — for example, billing details, if using Ghost(Pro).</p>
<hr>
<p>It''s a good idea to ask all of your users to fill out their user profiles, including bio and social links. These will populate rich structured data for posts and generally create more opportunities for themes to fully populate their design.</p>
</div>',NULL,'Ghost has a number of different user roles for your team

Authors
The base user level in Ghost is an author. Authors can write posts, edit their
own posts, and publish their own posts. Authors are trusted  users. If you don''t
trust users to be allowed to publish their own posts, you shouldn''t invite them
to Ghost admin.

Editors
Editors are the 2nd user level in Ghost. Editors can do everything that an
Author can do, but they can also edit and publish the posts of others - as well
as their own. Editors can also invite new authors to the site.

Administrators
The top user level in Ghost is Administrator. Again, administrators can do
everything that Authors and Editors can do, but they can also edit all site
settings and data, not just content. Additionally, administrators have full
access to invite, manage or remove any other user of the site.

The Owner
There is only ever one owner of a Ghost site. The owner is a special user which
has all the same permissions as an Administrator, but with two exceptions: The
Owner can never be deleted. And in some circumstances the owner will have access
to additional special settings if applicable — for example, billing details, if
using Ghost(Pro).


--------------------------------------------------------------------------------

It''s a good idea to ask all of your users to fill out their user profiles,
including bio and social links. These will populate rich structured data for
posts and generally create more opportunities for themes to fully populate their
design.','https://casper.ghost.org/v1.0.0/images/team.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','1','2017-08-22 08:37:02','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2c4c318d0030c6c64b','f6fb1c46-61de-4685-8772-79f507384814','Organising your content with tags','using-tags','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Ghost has a single, powerful organisational taxonomy, called tags.\n\nIt doesn''t matter whether you want to call them categories, tags, boxes, or anything else. You can think of Ghost tags a lot like Gmail labels. By tagging posts with one or more keyword, you can organise articles into buckets of related content.\n\n\n## Basic tagging\n\nWhen you write a post, you can assign tags to help differentiate between categories of content. For example, you might tag some posts with `News` and other posts with `Cycling`, which would create two distinct categories of content listed on `/tag/news/` and `/tag/cycling/`, respectively.\n\nIf you tag a post with both `News` *and* `Cycling` - then it appears in both sections.\n\nTag archives are like dedicated home-pages for each category of content that you have. They have their own pages, their own RSS feeds, and can support their own cover images and meta data.\n\n\n## The primary tag\n\nInside the Ghost editor, you can drag and drop tags into a specific order. The first tag in the list is always given the most importance, and some themes will only display the primary tag (the first tag in the list) by default. So you can add the most important tag which you want to show up in your theme, but also add a bunch of related tags which are less important.\n\n==**News**, Cycling, Bart Stevens, Extreme Sports==\n\nIn this example, **News** is the primary tag which will be displayed by the theme, but the post will also still receive all the other tags, and show up in their respective archives.\n\n\n## Private tags\n\nSometimes you may want to assign a post a specific tag, but you don''t necessarily want that tag appearing in the theme or creating an archive page. In Ghost, hashtags are private and can be used for special styling.\n\nFor example, if you sometimes publish posts with video content - you might want your theme to adapt and get rid of the sidebar for these posts, to give more space for an embedded video to fill the screen. In this case, you could use private tags to tell your theme what to do.\n\n==**News**, Cycling, #video==\n\nHere, the theme would assign the post publicly displayed tags of `News`, and `Cycling` - but it would also keep a private record of the post being tagged with `#video`.\n\nIn your theme, you could then look for private tags conditionally and give them special formatting:\n\n```\n{{#post}}\n    {{#has tag=\"#video\"}}\n        ...markup for a nice big video post layout...\n    {{else}}\n        ...regular markup for a post...\n    {{/has}}\n{{/post}}\n```\n\nYou can find documentation for theme development techniques like this and many more over on Ghost''s extensive [theme documentation](https://themes.ghost.org/)."}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Ghost has a single, powerful organisational taxonomy, called tags.</p>
<p>It doesn''t matter whether you want to call them categories, tags, boxes, or anything else. You can think of Ghost tags a lot like Gmail labels. By tagging posts with one or more keyword, you can organise articles into buckets of related content.</p>
<h2 id="basictagging">Basic tagging</h2>
<p>When you write a post, you can assign tags to help differentiate between categories of content. For example, you might tag some posts with <code>News</code> and other posts with <code>Cycling</code>, which would create two distinct categories of content listed on <code>/tag/news/</code> and <code>/tag/cycling/</code>, respectively.</p>
<p>If you tag a post with both <code>News</code> <em>and</em> <code>Cycling</code> - then it appears in both sections.</p>
<p>Tag archives are like dedicated home-pages for each category of content that you have. They have their own pages, their own RSS feeds, and can support their own cover images and meta data.</p>
<h2 id="theprimarytag">The primary tag</h2>
<p>Inside the Ghost editor, you can drag and drop tags into a specific order. The first tag in the list is always given the most importance, and some themes will only display the primary tag (the first tag in the list) by default. So you can add the most important tag which you want to show up in your theme, but also add a bunch of related tags which are less important.</p>
<p><mark><strong>News</strong>, Cycling, Bart Stevens, Extreme Sports</mark></p>
<p>In this example, <strong>News</strong> is the primary tag which will be displayed by the theme, but the post will also still receive all the other tags, and show up in their respective archives.</p>
<h2 id="privatetags">Private tags</h2>
<p>Sometimes you may want to assign a post a specific tag, but you don''t necessarily want that tag appearing in the theme or creating an archive page. In Ghost, hashtags are private and can be used for special styling.</p>
<p>For example, if you sometimes publish posts with video content - you might want your theme to adapt and get rid of the sidebar for these posts, to give more space for an embedded video to fill the screen. In this case, you could use private tags to tell your theme what to do.</p>
<p><mark><strong>News</strong>, Cycling, #video</mark></p>
<p>Here, the theme would assign the post publicly displayed tags of <code>News</code>, and <code>Cycling</code> - but it would also keep a private record of the post being tagged with <code>#video</code>.</p>
<p>In your theme, you could then look for private tags conditionally and give them special formatting:</p>
<pre><code>{{#post}}
    {{#has tag=&quot;#video&quot;}}
        ...markup for a nice big video post layout...
    {{else}}
        ...regular markup for a post...
    {{/has}}
{{/post}}
</code></pre>
<p>You can find documentation for theme development techniques like this and many more over on Ghost''s extensive <a href="https://themes.ghost.org/">theme documentation</a>.</p>
</div>',NULL,'Ghost has a single, powerful organisational taxonomy, called tags.

It doesn''t matter whether you want to call them categories, tags, boxes, or
anything else. You can think of Ghost tags a lot like Gmail labels. By tagging
posts with one or more keyword, you can organise articles into buckets of
related content.

Basic tagging
When you write a post, you can assign tags to help differentiate between
categories of content. For example, you might tag some posts with News  and
other posts with Cycling, which would create two distinct categories of content
listed on /tag/news/  and /tag/cycling/, respectively.

If you tag a post with both News  and  Cycling  - then it appears in both
sections.

Tag archives are like dedicated home-pages for each category of content that you
have. They have their own pages, their own RSS feeds, and can support their own
cover images and meta data.

The primary tag
Inside the Ghost editor, you can drag and drop tags into a specific order. The
first tag in the list is always given the most importance, and some themes will
only display the primary tag (the first tag in the list) by default. So you can
add the most important tag which you want to show up in your theme, but also add
a bunch of related tags which are less important.

News, Cycling, Bart Stevens, Extreme Sports

In this example, News  is the primary tag which will be displayed by the theme,
but the post will also still receive all the other tags, and show up in their
respective archives.

Private tags
Sometimes you may want to assign a post a specific tag, but you don''t
necessarily want that tag appearing in the theme or creating an archive page. In
Ghost, hashtags are private and can be used for special styling.

For example, if you sometimes publish posts with video content - you might want
your theme to adapt and get rid of the sidebar for these posts, to give more
space for an embedded video to fill the screen. In this case, you could use
private tags to tell your theme what to do.

News, Cycling, #video

Here, the theme would assign the post publicly displayed tags of News, and 
Cycling  - but it would also keep a private record of the post being tagged with
 #video.

In your theme, you could then look for private tags conditionally and give them
special formatting:

{{#post}}
    {{#has tag="#video"}}
        ...markup for a nice big video post layout...
    {{else}}
        ...regular markup for a post...
    {{/has}}
{{/post}}


You can find documentation for theme development techniques like this and many
more over on Ghost''s extensive theme documentation [https://themes.ghost.org/].','https://casper.ghost.org/v1.0.0/images/tags.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','1','2017-08-22 08:37:03','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2c4c318d0030c6c64c','f3726d4a-98bc-40f6-b0e4-addfd92d6a70','Using the Ghost editor','the-editor','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Ghost uses a language called **Markdown** to format text.\n\nWhen you go to edit a post and see special characters and colours intertwined between the words, those are Markdown shortcuts which tell Ghost what to do with the words in your document. The biggest benefit of Markdown is that you can quickly apply formatting as you type, without needing to pause.\n\nAt the bottom of the editor, you''ll find a toolbar with basic formatting options to help you get started as easily as possible. You''ll also notice that there''s a **?** icon, which contains more advanced shortcuts.\n\nFor now, though, let''s run you through some of the basics. You''ll want to make sure you''re editing this post in order to see all the Markdown we''ve used.\n\n\n## Formatting text\n\nThe most common shortcuts are of course, **bold** text, _italic_ text, and [hyperlinks](https://example.com). These generally make up the bulk of any document. You can type the characters out, but you can also use keyboard shortcuts.\n\n* `CMD/Ctrl + B` for Bold\n* `CMD/Ctrl + I` for Italic\n* `CMD/Ctrl + K` for a Link\n* `CMD/Ctrl + H` for a Heading (Press multiple times for h2/h3/h4/etc)\n\nWith just a couple of extra characters here and there, you''re well on your way to creating a beautifully formatted story.\n\n\n## Inserting images\n\nImages in Markdown look just the same as links, except they''re prefixed with an exclamation mark, like this:\n\n`![Image description](/path/to/image.jpg)`\n\n![Computer](https://casper.ghost.org/v1.0.0/images/computer.jpg)\n\nMost Markdown editors don''t make you type this out, though. In Ghost you can click on the image icon in the toolbar at the bottom of the editor, or you can just click and drag an image from your desktop directly into the editor. Both will upload the image for you and generate the appropriate Markdown.\n\n_**Important Note:** Ghost does not currently have automatic image resizing, so it''s always a good idea to make sure your images aren''t gigantic files **before** uploading them to Ghost._\n\n\n## Making lists\n\nLists in HTML are a formatting nightmare, but in Markdown they become an absolute breeze with just a couple of characters and a bit of smart automation. For numbered lists, just write out the numbers. For bullet lists, just use `*` or `-` or `+`. Like this:\n\n1. Crack the eggs over a bowl\n2. Whisk them together\n3. Make an omelette\n\nor\n\n- Remember to buy milk\n- Feed the cat\n- Come up with idea for next story\n\n\n## Adding quotes\n\nWhen you want to pull out a particularly good excerpt in the middle of a piece, you can use `>` at the beginning of a paragraph to turn it into a Blockquote. You might''ve seen this formatting before in email clients.\n\n> A well placed quote guides a reader through a story, helping them to understand the most important points being made\n\nAll themes handles blockquotes slightly differently. Sometimes they''ll look better kept shorter, while other times you can quote fairly hefty amounts of text and get away with it. Generally, the safest option is to use blockquotes sparingly.\n\n\n## Dividing things up\n\nIf you''re writing a piece in parts and you just feel like you need to divide a couple of sections distinctly from each other, a horizontal rule might be just what you need. Dropping `---` on a new line will create a sleek divider, anywhere you want it.\n\n---\n\nThis should get you going with the vast majority of what you need to do in the editor, but if you''re still curious about more advanced tips then check out the [Advanced Markdown Guide](/advanced-markdown/) - or if you''d rather learn about how Ghost taxononomies work, we''ve got a overview of [how to use Ghost tags](/using-tags/)."}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Ghost uses a language called <strong>Markdown</strong> to format text.</p>
<p>When you go to edit a post and see special characters and colours intertwined between the words, those are Markdown shortcuts which tell Ghost what to do with the words in your document. The biggest benefit of Markdown is that you can quickly apply formatting as you type, without needing to pause.</p>
<p>At the bottom of the editor, you''ll find a toolbar with basic formatting options to help you get started as easily as possible. You''ll also notice that there''s a <strong>?</strong> icon, which contains more advanced shortcuts.</p>
<p>For now, though, let''s run you through some of the basics. You''ll want to make sure you''re editing this post in order to see all the Markdown we''ve used.</p>
<h2 id="formattingtext">Formatting text</h2>
<p>The most common shortcuts are of course, <strong>bold</strong> text, <em>italic</em> text, and <a href="https://example.com">hyperlinks</a>. These generally make up the bulk of any document. You can type the characters out, but you can also use keyboard shortcuts.</p>
<ul>
<li><code>CMD/Ctrl + B</code> for Bold</li>
<li><code>CMD/Ctrl + I</code> for Italic</li>
<li><code>CMD/Ctrl + K</code> for a Link</li>
<li><code>CMD/Ctrl + H</code> for a Heading (Press multiple times for h2/h3/h4/etc)</li>
</ul>
<p>With just a couple of extra characters here and there, you''re well on your way to creating a beautifully formatted story.</p>
<h2 id="insertingimages">Inserting images</h2>
<p>Images in Markdown look just the same as links, except they''re prefixed with an exclamation mark, like this:</p>
<p><code>![Image description](/path/to/image.jpg)</code></p>
<p><img src="https://casper.ghost.org/v1.0.0/images/computer.jpg" alt="Computer"></p>
<p>Most Markdown editors don''t make you type this out, though. In Ghost you can click on the image icon in the toolbar at the bottom of the editor, or you can just click and drag an image from your desktop directly into the editor. Both will upload the image for you and generate the appropriate Markdown.</p>
<p><em><strong>Important Note:</strong> Ghost does not currently have automatic image resizing, so it''s always a good idea to make sure your images aren''t gigantic files <strong>before</strong> uploading them to Ghost.</em></p>
<h2 id="makinglists">Making lists</h2>
<p>Lists in HTML are a formatting nightmare, but in Markdown they become an absolute breeze with just a couple of characters and a bit of smart automation. For numbered lists, just write out the numbers. For bullet lists, just use <code>*</code> or <code>-</code> or <code>+</code>. Like this:</p>
<ol>
<li>Crack the eggs over a bowl</li>
<li>Whisk them together</li>
<li>Make an omelette</li>
</ol>
<p>or</p>
<ul>
<li>Remember to buy milk</li>
<li>Feed the cat</li>
<li>Come up with idea for next story</li>
</ul>
<h2 id="addingquotes">Adding quotes</h2>
<p>When you want to pull out a particularly good excerpt in the middle of a piece, you can use <code>&gt;</code> at the beginning of a paragraph to turn it into a Blockquote. You might''ve seen this formatting before in email clients.</p>
<blockquote>
<p>A well placed quote guides a reader through a story, helping them to understand the most important points being made</p>
</blockquote>
<p>All themes handles blockquotes slightly differently. Sometimes they''ll look better kept shorter, while other times you can quote fairly hefty amounts of text and get away with it. Generally, the safest option is to use blockquotes sparingly.</p>
<h2 id="dividingthingsup">Dividing things up</h2>
<p>If you''re writing a piece in parts and you just feel like you need to divide a couple of sections distinctly from each other, a horizontal rule might be just what you need. Dropping <code>---</code> on a new line will create a sleek divider, anywhere you want it.</p>
<hr>
<p>This should get you going with the vast majority of what you need to do in the editor, but if you''re still curious about more advanced tips then check out the <a href="/advanced-markdown/">Advanced Markdown Guide</a> - or if you''d rather learn about how Ghost taxononomies work, we''ve got a overview of <a href="/using-tags/">how to use Ghost tags</a>.</p>
</div>',NULL,'Ghost uses a language called Markdown  to format text.

When you go to edit a post and see special characters and colours intertwined
between the words, those are Markdown shortcuts which tell Ghost what to do with
the words in your document. The biggest benefit of Markdown is that you can
quickly apply formatting as you type, without needing to pause.

At the bottom of the editor, you''ll find a toolbar with basic formatting options
to help you get started as easily as possible. You''ll also notice that there''s a
 ?  icon, which contains more advanced shortcuts.

For now, though, let''s run you through some of the basics. You''ll want to make
sure you''re editing this post in order to see all the Markdown we''ve used.

Formatting text
The most common shortcuts are of course, bold  text, italic  text, and 
hyperlinks [https://example.com]. These generally make up the bulk of any
document. You can type the characters out, but you can also use keyboard
shortcuts.

 * CMD/Ctrl + B  for Bold
 * CMD/Ctrl + I  for Italic
 * CMD/Ctrl + K  for a Link
 * CMD/Ctrl + H  for a Heading (Press multiple times for h2/h3/h4/etc)

With just a couple of extra characters here and there, you''re well on your way
to creating a beautifully formatted story.

Inserting images
Images in Markdown look just the same as links, except they''re prefixed with an
exclamation mark, like this:

![Image description](/path/to/image.jpg)



Most Markdown editors don''t make you type this out, though. In Ghost you can
click on the image icon in the toolbar at the bottom of the editor, or you can
just click and drag an image from your desktop directly into the editor. Both
will upload the image for you and generate the appropriate Markdown.

Important Note:  Ghost does not currently have automatic image resizing, so it''s
always a good idea to make sure your images aren''t gigantic files before 
uploading them to Ghost.

Making lists
Lists in HTML are a formatting nightmare, but in Markdown they become an
absolute breeze with just a couple of characters and a bit of smart automation.
For numbered lists, just write out the numbers. For bullet lists, just use *  or
 -  or +. Like this:

 1. Crack the eggs over a bowl
 2. Whisk them together
 3. Make an omelette

or

 * Remember to buy milk
 * Feed the cat
 * Come up with idea for next story

Adding quotes
When you want to pull out a particularly good excerpt in the middle of a piece,
you can use >  at the beginning of a paragraph to turn it into a Blockquote. You
might''ve seen this formatting before in email clients.

A well placed quote guides a reader through a story, helping them to understand
the most important points being made

All themes handles blockquotes slightly differently. Sometimes they''ll look
better kept shorter, while other times you can quote fairly hefty amounts of
text and get away with it. Generally, the safest option is to use blockquotes
sparingly.

Dividing things up
If you''re writing a piece in parts and you just feel like you need to divide a
couple of sections distinctly from each other, a horizontal rule might be just
what you need. Dropping ---  on a new line will create a sleek divider, anywhere
you want it.


--------------------------------------------------------------------------------

This should get you going with the vast majority of what you need to do in the
editor, but if you''re still curious about more advanced tips then check out the 
Advanced Markdown Guide [/advanced-markdown/]  - or if you''d rather learn about
how Ghost taxononomies work, we''ve got a overview of how to use Ghost tags
[/using-tags/].','https://casper.ghost.org/v1.0.0/images/writing.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','1','2017-08-22 08:37:04','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('599bed2c4c318d0030c6c64d','b4cdbaef-abbe-4f30-87b5-e4ad5ca55b0d','Welcome to Ghost','welcome','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Hey! Welcome to Ghost, it''s great to have you :)\n\nWe know that first impressions are important, so we''ve populated your new site with some initial **Getting Started** posts that will help you get familiar with everything in no time. This is the first one!\n\n\n### There are a few things that you should know up-front:\n\n1. Ghost is designed for ambitious, professional publishers who want to actively build a business around their content. That''s who it works best for. If you''re using Ghost for some other purpose, that''s fine too - but it might not be the best choice for you.\n\n2. The entire platform can be modified and customized to suit your needs, which is very powerful, but doing so **does** require some knowledge of code. Ghost is not necessarily a good platform for beginners or people who just want a simple personal blog.\n\n3. For the best experience we recommend downloading the [Ghost Desktop App](https://ghost.org/downloads/) for your computer, which is the best way to access your Ghost site on a desktop device.\n\nGhost is made by an independent non-profit organisation called the Ghost Foundation. We are 100% self funded by revenue from our [Ghost(Pro)](https://ghost.org/pricing) service, and every penny we make is re-invested into funding further development of free, open source technology for modern journalism.\n\nThe main thing you''ll want to read about next is probably: [the Ghost editor](/the-editor/).\n\nOnce you''re done reading, you can simply delete the default **Ghost** user from your team to remove all of these introductory posts!"}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Hey! Welcome to Ghost, it''s great to have you :)</p>
<p>We know that first impressions are important, so we''ve populated your new site with some initial <strong>Getting Started</strong> posts that will help you get familiar with everything in no time. This is the first one!</p>
<h3 id="thereareafewthingsthatyoushouldknowupfront">There are a few things that you should know up-front:</h3>
<ol>
<li>
<p>Ghost is designed for ambitious, professional publishers who want to actively build a business around their content. That''s who it works best for. If you''re using Ghost for some other purpose, that''s fine too - but it might not be the best choice for you.</p>
</li>
<li>
<p>The entire platform can be modified and customized to suit your needs, which is very powerful, but doing so <strong>does</strong> require some knowledge of code. Ghost is not necessarily a good platform for beginners or people who just want a simple personal blog.</p>
</li>
<li>
<p>For the best experience we recommend downloading the <a href="https://ghost.org/downloads/">Ghost Desktop App</a> for your computer, which is the best way to access your Ghost site on a desktop device.</p>
</li>
</ol>
<p>Ghost is made by an independent non-profit organisation called the Ghost Foundation. We are 100% self funded by revenue from our <a href="https://ghost.org/pricing">Ghost(Pro)</a> service, and every penny we make is re-invested into funding further development of free, open source technology for modern journalism.</p>
<p>The main thing you''ll want to read about next is probably: <a href="/the-editor/">the Ghost editor</a>.</p>
<p>Once you''re done reading, you can simply delete the default <strong>Ghost</strong> user from your team to remove all of these introductory posts!</p>
</div>',NULL,'Hey! Welcome to Ghost, it''s great to have you :)

We know that first impressions are important, so we''ve populated your new site
with some initial Getting Started  posts that will help you get familiar with
everything in no time. This is the first one!

There are a few things that you should know up-front:
 1. Ghost is designed for ambitious, professional publishers who want to
    actively build a business around their content. That''s who it works best
    for. If you''re using Ghost for some other purpose, that''s fine too - but it
    might not be the best choice for you.
    
    
 2. The entire platform can be modified and customized to suit your needs, which
    is very powerful, but doing so does  require some knowledge of code. Ghost
    is not necessarily a good platform for beginners or people who just want a
    simple personal blog.
    
    
 3. For the best experience we recommend downloading the Ghost Desktop App
    [https://ghost.org/downloads/]  for your computer, which is the best way to
    access your Ghost site on a desktop device.
    
    

Ghost is made by an independent non-profit organisation called the Ghost
Foundation. We are 100% self funded by revenue from our Ghost(Pro)
[https://ghost.org/pricing]  service, and every penny we make is re-invested
into funding further development of free, open source technology for modern
journalism.

The main thing you''ll want to read about next is probably: the Ghost editor
[/the-editor/].

Once you''re done reading, you can simply delete the default Ghost  user from
your team to remove all of these introductory posts!','https://casper.ghost.org/v1.0.0/images/welcome.jpg',0,0,'published',NULL,'public',NULL,NULL,'5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','5951f5fca366002ebd5dbef7','2017-08-22 08:37:00','1','2017-08-22 08:37:05','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "posts" VALUES('5a3ba3ff1536930001346e53','fd63569d-fb95-4372-bbd8-e8e5e29eb346','银魂','yin-hun','{"version":"0.3.1","markups":[],"atoms":[],"cards":[["card-markdown",{"cardName":"card-markdown","markdown":"Ghost"}]],"sections":[[10,0]]}','<div class="kg-card-markdown"><p>Ghost</p>
</div>',NULL,'Ghost',NULL,0,0,'published',NULL,'public',NULL,NULL,'1','2017-12-21 12:07:27','1','2017-12-21 12:07:55','1','2017-12-21 12:07:52','1',NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL);
CREATE TABLE "users" ("id" varchar(24) not null, "name" varchar(191) not null, "slug" varchar(191) not null, "ghost_auth_access_token" varchar(32) null, "ghost_auth_id" varchar(24) null, "password" varchar(60) not null, "email" varchar(191) not null, "profile_image" varchar(2000) null, "cover_image" varchar(2000) null, "bio" text null, "website" varchar(2000) null, "location" text null, "facebook" varchar(2000) null, "twitter" varchar(2000) null, "accessibility" text null, "status" varchar(50) not null default 'active', "locale" varchar(6) null, "visibility" varchar(50) not null default 'public', "meta_title" varchar(2000) null, "meta_description" varchar(2000) null, "tour" text null, "last_seen" datetime null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "users" VALUES('1','JamesYang','jamesyang',NULL,NULL,'$2a$10$7Ng.4gQXVfEb3SCvjraf4erCn2e5RdQxK5UNhN9TcB3v08msFAXhO','drawnkid@yeah.net','/content/images/2017/12/Akali_Splash_Tile_5.jpg','/content/images/2017/12/-----2017-09-26---10.24.47.png',NULL,NULL,NULL,NULL,NULL,NULL,'active',NULL,'public',NULL,NULL,NULL,'2017-12-21 12:01:35','2017-08-22 08:37:01','1','2017-12-21 12:06:56','1');
INSERT INTO "users" VALUES('5951f5fca366002ebd5dbef7','Ghost','ghost',NULL,NULL,'$2a$10$ryV2TXLXZh0.7s.ODLTsTuhFkqHa.DJ9nFxox5m1kgC4ka4eRXLQe','ghost-author@example.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'active',NULL,'public',NULL,NULL,NULL,NULL,'2017-08-22 08:37:02','1','2017-08-22 08:37:02','1');
CREATE TABLE "roles" ("id" varchar(24) not null, "name" varchar(50) not null, "description" varchar(2000) null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "roles" VALUES('599bed2c4c318d0030c6c652','Administrator','Administrators','2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "roles" VALUES('599bed2c4c318d0030c6c653','Editor','Editors','2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "roles" VALUES('599bed2c4c318d0030c6c654','Author','Authors','2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "roles" VALUES('599bed2c4c318d0030c6c655','Owner','Blog Owner','2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
CREATE TABLE "roles_users" ("id" varchar(24) not null, "role_id" varchar(24) not null, "user_id" varchar(24) not null, primary key ("id"));
INSERT INTO "roles_users" VALUES('599bed304c318d0030c6c687','599bed2c4c318d0030c6c654','5951f5fca366002ebd5dbef7');
INSERT INTO "roles_users" VALUES('599bed304c318d0030c6c6f1','599bed2c4c318d0030c6c655','1');
CREATE TABLE "permissions" ("id" varchar(24) not null, "name" varchar(50) not null, "object_type" varchar(50) not null, "action_type" varchar(50) not null, "object_id" varchar(24) null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c656','Export database','db','exportContent',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c657','Import database','db','importContent',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c658','Delete all content','db','deleteAllContent',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c659','Send mail','mail','send',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65a','Browse notifications','notification','browse',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65b','Add notifications','notification','add',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65c','Delete notifications','notification','destroy',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65d','Browse posts','post','browse',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65e','Read posts','post','read',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c65f','Edit posts','post','edit',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c660','Add posts','post','add',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c661','Delete posts','post','destroy',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c662','Browse settings','setting','browse',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c663','Read settings','setting','read',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c664','Edit settings','setting','edit',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c665','Generate slugs','slug','generate',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c666','Browse tags','tag','browse',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c667','Read tags','tag','read',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c668','Edit tags','tag','edit',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c669','Add tags','tag','add',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c66a','Delete tags','tag','destroy',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c66b','Browse themes','theme','browse',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c66c','Edit themes','theme','edit',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c66d','Activate themes','theme','activate',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2c4c318d0030c6c66e','Upload themes','theme','add',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c66f','Download themes','theme','read',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c670','Delete themes','theme','destroy',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c671','Browse users','user','browse',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c672','Read users','user','read',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c673','Edit users','user','edit',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c674','Add users','user','add',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c675','Delete users','user','destroy',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c676','Assign a role','role','assign',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c677','Browse roles','role','browse',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c678','Browse clients','client','browse',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c679','Read clients','client','read',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67a','Edit clients','client','edit',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67b','Add clients','client','add',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67c','Delete clients','client','destroy',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67d','Browse subscribers','subscriber','browse',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67e','Read subscribers','subscriber','read',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c67f','Edit subscribers','subscriber','edit',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c680','Add subscribers','subscriber','add',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c681','Delete subscribers','subscriber','destroy',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c682','Browse invites','invite','browse',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c683','Read invites','invite','read',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c684','Edit invites','invite','edit',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c685','Add invites','invite','add',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
INSERT INTO "permissions" VALUES('599bed2d4c318d0030c6c686','Delete invites','invite','destroy',NULL,'2017-08-22 08:37:01','1','2017-08-22 08:37:01','1');
CREATE TABLE "permissions_users" ("id" varchar(24) not null, "user_id" varchar(24) not null, "permission_id" varchar(24) not null, primary key ("id"));
CREATE TABLE "permissions_roles" ("id" varchar(24) not null, "role_id" varchar(24) not null, "permission_id" varchar(24) not null, primary key ("id"));
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c688','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c656');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c689','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c657');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68a','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c658');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68b','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c659');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68c','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68d','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65b');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68e','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65c');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c68f','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65d');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c690','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65e');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c691','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c65f');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c692','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c660');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c693','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c661');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c694','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c662');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c695','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c663');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c696','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c664');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c697','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c665');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c698','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c666');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c699','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c667');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69a','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c668');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69b','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c669');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69c','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c66a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69d','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c66b');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69e','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c66c');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c69f','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c66d');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a0','599bed2c4c318d0030c6c652','599bed2c4c318d0030c6c66e');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a1','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c66f');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a2','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c670');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a3','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c671');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a4','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c672');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a5','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c673');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a6','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c674');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a7','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c675');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a8','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c676');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6a9','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c677');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6aa','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c678');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ab','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c679');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ac','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ad','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67b');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ae','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67c');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6af','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67d');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b0','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67e');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b1','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c67f');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b2','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c680');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b3','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c681');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b4','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c682');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b5','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c683');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b6','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c684');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b7','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c685');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b8','599bed2c4c318d0030c6c652','599bed2d4c318d0030c6c686');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6b9','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c65d');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ba','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c65e');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6bb','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c65f');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6bc','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c660');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6bd','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c661');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6be','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c662');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6bf','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c663');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c0','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c665');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c1','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c666');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c2','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c667');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c3','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c668');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c4','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c669');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c5','599bed2c4c318d0030c6c653','599bed2c4c318d0030c6c66a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c6','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c671');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c7','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c672');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c8','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c673');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6c9','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c674');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ca','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c675');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6cb','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c676');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6cc','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c677');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6cd','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c678');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6ce','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c679');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6cf','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c67a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d0','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c67b');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d1','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c67c');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d2','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c680');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d3','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c682');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d4','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c683');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d5','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c684');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d6','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c685');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d7','599bed2c4c318d0030c6c653','599bed2d4c318d0030c6c686');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d8','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c65d');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6d9','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c65e');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6da','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c660');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6db','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c662');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6dc','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c663');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6dd','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c665');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6de','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c666');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6df','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c667');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e0','599bed2c4c318d0030c6c654','599bed2c4c318d0030c6c669');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e1','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c671');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e2','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c672');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e3','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c677');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e4','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c678');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e5','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c679');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e6','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c67a');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e7','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c67b');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e8','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c67c');
INSERT INTO "permissions_roles" VALUES('599bed304c318d0030c6c6e9','599bed2c4c318d0030c6c654','599bed2d4c318d0030c6c680');
CREATE TABLE "permissions_apps" ("id" varchar(24) not null, "app_id" varchar(24) not null, "permission_id" varchar(24) not null, primary key ("id"));
CREATE TABLE "settings" ("id" varchar(24) not null, "key" varchar(50) not null, "value" text null, "type" varchar(50) not null default 'core', "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e88','db_hash','b71e6de6-29da-4785-919c-669120274f93','core','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e89','next_update_check','1513944061','core','2017-08-22 08:37:07','1','2017-12-21 12:01:01','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8a','display_update_notification','1.19.0','core','2017-08-22 08:37:07','1','2017-12-21 12:01:01','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8b','seen_notifications','[]','core','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8c','title','James','blog','2017-08-22 08:37:07','1','2017-12-21 12:01:34','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8d','description','Thoughts, stories and ideas.','blog','2017-08-22 08:37:07','1','2017-12-21 12:01:34','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8e','logo','https://casper.ghost.org/v1.0.0/images/ghost-logo.svg','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e8f','cover_image','https://casper.ghost.org/v1.0.0/images/blog-cover.jpg','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e90','icon','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e91','default_locale','en','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e92','active_timezone','Etc/UTC','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e93','force_i18n','true','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e94','permalinks','/:slug/','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e95','amp','true','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e96','ghost_head','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e97','ghost_foot','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e98','facebook','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e99','twitter','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9a','labs','{"publicAPI": true}','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9b','navigation','[{"label":"Home", "url":"/"}]','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9c','slack','[{"url":""}]','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9d','unsplash','','blog','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9e','active_theme','casper','theme','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231e9f','active_apps','[]','app','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231ea0','installed_apps','[]','app','2017-08-22 08:37:07','1','2017-12-21 11:36:55','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231ea1','is_private','false','private','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
INSERT INTO "settings" VALUES('599bed33e88b7d0001231ea2','password','','private','2017-08-22 08:37:07','1','2017-08-22 08:37:07','1');
CREATE TABLE "tags" ("id" varchar(24) not null, "name" varchar(191) not null, "slug" varchar(191) not null, "description" text null, "feature_image" varchar(2000) null, "parent_id" varchar(191) null, "visibility" varchar(50) not null default 'public', "meta_title" varchar(2000) null, "meta_description" varchar(2000) null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "tags" VALUES('599bed2c4c318d0030c6c64e','Getting Started','getting-started',NULL,NULL,NULL,'public',NULL,NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
CREATE TABLE "posts_tags" ("id" varchar(24) not null, "post_id" varchar(24) not null, "tag_id" varchar(24) not null, "sort_order" integer not null default '0', foreign key("post_id") references "posts"("id"), foreign key("tag_id") references "tags"("id"), primary key ("id"));
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6ea','599bed2b4c318d0030c6c647','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6eb','599bed2b4c318d0030c6c648','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6ec','599bed2c4c318d0030c6c649','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6ed','599bed2c4c318d0030c6c64a','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6ee','599bed2c4c318d0030c6c64b','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6ef','599bed2c4c318d0030c6c64c','599bed2c4c318d0030c6c64e',0);
INSERT INTO "posts_tags" VALUES('599bed304c318d0030c6c6f0','599bed2c4c318d0030c6c64d','599bed2c4c318d0030c6c64e',0);
CREATE TABLE "apps" ("id" varchar(24) not null, "name" varchar(191) not null, "slug" varchar(191) not null, "version" varchar(50) not null, "status" varchar(50) not null default 'inactive', "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
CREATE TABLE "app_settings" ("id" varchar(24) not null, "key" varchar(50) not null, "value" text null, "app_id" varchar(24) not null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, foreign key("app_id") references "apps"("id"), primary key ("id"));
CREATE TABLE "app_fields" ("id" varchar(24) not null, "key" varchar(50) not null, "value" text null, "type" varchar(50) not null default 'html', "app_id" varchar(24) not null, "relatable_id" varchar(24) not null, "relatable_type" varchar(50) not null default 'posts', "active" boolean not null default '1', "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, foreign key("app_id") references "apps"("id"), primary key ("id"));
CREATE TABLE "clients" ("id" varchar(24) not null, "uuid" varchar(36) not null, "name" varchar(50) not null, "slug" varchar(50) not null, "secret" varchar(191) not null, "redirection_uri" varchar(2000) null, "client_uri" varchar(2000) null, "auth_uri" varchar(2000) null, "logo" varchar(2000) null, "status" varchar(50) not null default 'development', "type" varchar(50) not null default 'ua', "description" varchar(2000) null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
INSERT INTO "clients" VALUES('599bed2c4c318d0030c6c64f','b97fb11f-1e35-4434-82e4-12c294fbb2b4','Ghost Admin','ghost-admin','26dd255ac8a9',NULL,NULL,NULL,NULL,'enabled','ua',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "clients" VALUES('599bed2c4c318d0030c6c650','7e5f52d6-d7a7-4d8a-bede-b08fdcf875ea','Ghost Frontend','ghost-frontend','6fc083c4ec4d',NULL,NULL,NULL,NULL,'enabled','ua',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
INSERT INTO "clients" VALUES('599bed2c4c318d0030c6c651','e5670f35-640d-463d-abd1-2aa736ac165c','Ghost Scheduler','ghost-scheduler','f67c289e77e8',NULL,NULL,NULL,NULL,'enabled','web',NULL,'2017-08-22 08:37:00','1','2017-08-22 08:37:00','1');
CREATE TABLE "client_trusted_domains" ("id" varchar(24) not null, "client_id" varchar(24) not null, "trusted_domain" varchar(2000) null, foreign key("client_id") references "clients"("id"), primary key ("id"));
CREATE TABLE "accesstokens" ("id" varchar(24) not null, "token" varchar(191) not null, "user_id" varchar(24) not null, "client_id" varchar(24) not null, "issued_by" varchar(24) null, "expires" bigint not null, foreign key("user_id") references "users"("id"), foreign key("client_id") references "clients"("id"), primary key ("id"));
INSERT INTO "accesstokens" VALUES('5a3ba29f1536930001346e52','EnNwNnADtcHS7JcbPXWHOfGSFx8EkVeIgLX6znYnZHFKfJyBH26a9CBTADtF89o2QR0OHJr58rqjRKe7wgeud2ZIGuGz3BqeVMCKc84ai1iigdzN6o2nmW3XbnvYUpvlKvq07N8zu2m28asiuqfw4VhSK7SAACN0YQgxBUtkH7vsrReAyVNvFZN2SYM0blH','1','599bed2c4c318d0030c6c64f','5a3ba29f1536930001346e51',1516485695589);
CREATE TABLE "refreshtokens" ("id" varchar(24) not null, "token" varchar(191) not null, "user_id" varchar(24) not null, "client_id" varchar(24) not null, "expires" bigint not null, foreign key("user_id") references "users"("id"), foreign key("client_id") references "clients"("id"), primary key ("id"));
INSERT INTO "refreshtokens" VALUES('5a3ba29f1536930001346e51','MPFG7II4s0DNj0Ka4s55lVkyY0uKQJnO6BE6RYTODbJIJPtnzxsOhKvGBdXxT4NZYiiSaGgQfGuELnLxvbknb6hGMBTotHlg0bVVKFPYfDQgAI6zLny5HulHXXzNv7t73ifDPIxeLuYGIEe5P9vlGnIwxURGuWU3iSo7lYrnfAQjjP8vX4VK4GuyNakHnRW','1','599bed2c4c318d0030c6c64f',1529625695589);
CREATE TABLE "subscribers" ("id" varchar(24) not null, "name" varchar(191) null, "email" varchar(191) not null, "status" varchar(50) not null default 'pending', "post_id" varchar(24) null, "subscribed_url" varchar(2000) null, "subscribed_referrer" varchar(2000) null, "unsubscribed_url" varchar(2000) null, "unsubscribed_at" datetime null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
CREATE TABLE "invites" ("id" varchar(24) not null, "role_id" varchar(24) not null, "status" varchar(50) not null default 'pending', "token" varchar(191) not null, "email" varchar(191) not null, "expires" bigint not null, "created_at" datetime not null, "created_by" varchar(24) not null, "updated_at" datetime null, "updated_by" varchar(24) null, primary key ("id"));
CREATE TABLE "brute" ("key" varchar(191) not null, "firstRequest" bigint not null, "lastRequest" bigint not null, "lifetime" bigint not null, "count" integer not null);
INSERT INTO "brute" VALUES('4bh+T1yPxvG1HJHJvLYQeGJ4o/YpTJiDzsyJz13W/bI=',1513857695192,1513857695192,1513861295222,1);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('migrations',5);
CREATE UNIQUE INDEX "posts_slug_unique" on "posts" ("slug");
CREATE UNIQUE INDEX "users_slug_unique" on "users" ("slug");
CREATE UNIQUE INDEX "users_email_unique" on "users" ("email");
CREATE UNIQUE INDEX "roles_name_unique" on "roles" ("name");
CREATE UNIQUE INDEX "permissions_name_unique" on "permissions" ("name");
CREATE UNIQUE INDEX "settings_key_unique" on "settings" ("key");
CREATE UNIQUE INDEX "tags_slug_unique" on "tags" ("slug");
CREATE UNIQUE INDEX "apps_name_unique" on "apps" ("name");
CREATE UNIQUE INDEX "apps_slug_unique" on "apps" ("slug");
CREATE UNIQUE INDEX "app_settings_key_unique" on "app_settings" ("key");
CREATE UNIQUE INDEX "clients_name_unique" on "clients" ("name");
CREATE UNIQUE INDEX "clients_slug_unique" on "clients" ("slug");
CREATE UNIQUE INDEX "accesstokens_token_unique" on "accesstokens" ("token");
CREATE UNIQUE INDEX "refreshtokens_token_unique" on "refreshtokens" ("token");
CREATE UNIQUE INDEX "subscribers_email_unique" on "subscribers" ("email");
CREATE UNIQUE INDEX "invites_token_unique" on "invites" ("token");
CREATE UNIQUE INDEX "invites_email_unique" on "invites" ("email");
COMMIT;
