---
title: Stop Drop | MoSadie
---

# Description
foo bar this is a test of words and things. This page is a work in progress test of GitHub pages as an auto-generated changelog page.

{% if site.github.releases.size > 0 %}
# Versions:
{% for release in site.github.releases %}

| Version | Title | Changelog | Downloads |
| ------- | ----- | --------- | --------- |
| {% if release.prerelease %} Pre-release: {% endif %} {{release.tag_name}} | {{release.name}} | [Changelog]({{release.html_url}}) | {% for asset in release.assets %} [{{asset.name}}]({{asset.browser_download_url}}) <br> {% endfor %}|

<br>
{% endfor %}
{% endif %}