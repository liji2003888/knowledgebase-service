/**
* @fileoverview Implements toMarkRendererCreator.
* @author NHN FE Development Lab <dl_javascript@nhn.com>
*/
import $ from 'jquery';
import util from 'tui-code-snippet';
import toMark from 'to-mark';

const RX_COLS = /@cols=[0-9]+:/g;

/**
 * Create repeat string.
 * @param {string} str - target string
 * @param {number} count - count
 * @returns {string}
 * @private
 */
function createRepeatString(str, count) {
  return util.range(0, count).map(() => str).join('');
}

/**
 * Make table head align text.
 * Copy from https://github.com/nhn/to-mark/blob/develop/src/renderer.gfm.js
 * @param {HTMLElement} thElement - Table head cell element
 * @returns {string}
 * @private
 */
function makeTableHeadAlignText(thElement) {
  const { align } = thElement;
  const textContent = (thElement.textContent || thElement.innerText).replace(RX_COLS, '');
  let textLength = textContent.length;
  let leftAlignValue = '';
  let rightAlignValue = '';

  if (align) {
    if (align === 'left') {
      leftAlignValue = ':';
      textLength -= 1;
    } else if (align === 'right') {
      rightAlignValue = ':';
      textLength -= 1;
    } else if (align === 'center') {
      rightAlignValue = ':';
      leftAlignValue = ':';
      textLength -= 2;
    }
  }

  textLength = Math.max(textLength, 3);

  return leftAlignValue + createRepeatString('-', textLength) + rightAlignValue;
}

/**
 * Get additional th element count.
 * @param {Array.<HTMLElement>} ths - th element list
 * @private
 * @returns {Number}
 */
export function getAdditionalThCount(ths) {
  let additionalThCount = 0;

  ths.filter(th => $(th).attr('colspan')).forEach((th) => {
    additionalThCount += (parseInt($(th).attr('colspan'), 10) - 1);
  });

  return additionalThCount;
}

/**
 * Create thead markdown.
 * @param {HTMLElement} theadElement - theadElement element
 * @param {string} theadContentMarkdown - thead markdown content
 * @returns {string}
 * @private
 */
export function createTheadMarkdown(theadElement, theadContentMarkdown) {
  const ths = $(theadElement).find('th').get();
  let align = ths.map(th => ` ${makeTableHeadAlignText(th)} |`).join('');

  align += createRepeatString(' --- |', getAdditionalThCount(ths));

  return theadContentMarkdown ? `${theadContentMarkdown}|${align}\n` : '';
}

export default toMark.Renderer.factory(toMark.gfmRenderer, {
  THEAD: createTheadMarkdown,
});
