import {createMuiTheme, colors} from '@material-ui/core';

let theme = createMuiTheme({
    palette: {
        background: {
            global: colors.grey[200],
        },
        primary: {
            main: '#11c1ff',
            text: '#FFFFFF'
        },
        error: {
            main: colors.red[400],
        },
    }
});

export {theme}