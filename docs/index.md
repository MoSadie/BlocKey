---
title: Stop Drop | MoSadie
---

# Description
foo bar this is a test of words and things. This page is a work in progress test of GitHub pages as an auto-generated changelog page.

{{site.github.releases}}

{% if site.github.releases.size > 0 %}
# Versions:

{% for release in site.github.releases %}
## {% if release.prerelease %} Pre-release: {% endif %}{{release.name}} ##

### Changelog
{{release.description}}

###Downloads:
{% for asset in release.assets %}
 * [{{asset.name}}]({{asset.browser_download_url}})
{% endfor %}

{% endfor %}
{% endif %}