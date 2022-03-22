import Marked from 'marked';
import HighLight from 'highlight.js';
import { cleanUrl } from 'marked/src/helpers';

export default class MarkedWrapper {
  constructor(src) {
    this.headerList = [];
    this.t3 = [];
    this.idStart = 0;
    this.src = src;
    this.init();
  }

  init() {
    const self = this;
    const renderer = {
      heading(text, level, raw, slugger) {
        self.headerList.push({ title: text, level: level, raw: raw, id: self.idStart });
        if (this.options.headerIds) {
          return (
            '<h' +
                        level +
                        ' id="' +
                        (this.options.headerPrefix +
                            self.idStart++) +
                        '" ' + '>' +
                        text +
                        '</h' +
                        level +
                        '>\n'
          );
        }
        // ignore IDs
        return '<h' + level + '>' + text + '</h' + level + '>\n';
      },

      // 重写image方法
      image(href, title, text) {
        href = cleanUrl(this.options.sanitize, this.options.baseUrl, href);
        if (href === null) {
          return text;
        }

        let out = '<img src="' + href + '" alt="' + text + '"';
        if (title) {
          out += ' title="' + title + '"';
        }
        const width =
                    "style='width:auto;height:auto;max-width:100%;max-height:100%'";
        out += width;
        out += this.options.xhtml ? '/>' : '>';
        return out;
      }
    };
    Marked.use({ renderer });
    Marked.setOptions({
      highlight: function (code) {
        return HighLight.highlightAuto(code).value;
      },
      pedantic: false,
      gfm: true,
      tables: true,
      breaks: false,
      sanitize: false,
      smartLists: true,
      smartypants: false,
      xhtml: false
      // headerPrefix: 'noodb_'
    });
  }

  setSrc(src) {
    this.src = src;
  }

  getHeaderList() {
    if (this.headerList.length === 0) {
      this.renderer();
    }
    return this.headerList;
  }

  renderer() {
    return Marked(this.src);
  }
}
