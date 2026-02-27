/**
 * 暗黑模式切换
 * Dark Mode Toggle
 */
(function() {
    'use strict';
    
    const STORAGE_KEY = 'blog-theme';
    const DARK_CLASS = 'dark-mode';
    
    // 初始化主题
    function initTheme() {
        const savedTheme = localStorage.getItem(STORAGE_KEY);
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        
        if (savedTheme === 'dark' || (!savedTheme && prefersDark)) {
            document.body.classList.add(DARK_CLASS);
        }
    }
    
    // 切换主题
    function toggleTheme() {
        const isDark = document.body.classList.toggle(DARK_CLASS);
        localStorage.setItem(STORAGE_KEY, isDark ? 'dark' : 'light');
        
        // 触发事件，让其他组件知道主题变化
        window.dispatchEvent(new CustomEvent('themechange', { 
            detail: { theme: isDark ? 'dark' : 'light' } 
        }));
    }
    
    // 获取当前主题
    function getCurrentTheme() {
        return document.body.classList.contains(DARK_CLASS) ? 'dark' : 'light';
    }
    
    // 暴露到全局
    window.ThemeManager = {
        init: initTheme,
        toggle: toggleTheme,
        getTheme: getCurrentTheme
    };
    
    // DOM 加载完成后初始化
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initTheme);
    } else {
        initTheme();
    }
})();
