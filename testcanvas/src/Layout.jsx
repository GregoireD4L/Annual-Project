import React from 'react';
import {AppBar, withStyles} from '@material-ui/core';
import PropTypes from 'prop-types';

const styles = theme => ({
    root: {
        backgroundColor: theme.palette.background.global
    },
    main: {
        minHeight: '85vh',
        marginTop: 64,
        padding: 1
    },
    footer: {
        fontSize: '14px',
        fontFamily: theme.typography.fontFamily,
        fontWeight: 300,
        padding: "8px",
        textAlign: "center",
        //position: "absolute",
        //bottom: '10px',
        color: theme.palette.text.secondary
    },
    appBar: {
        transition: "all 200ms ease-in-out"
    },
});


class Layout extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        let { classes, appBar, children, footer, appBarStyle } = this.props;
        return (
            <section className={classes.root} >
                <AppBar position="fixed" classes={{root:classes.appBar}} style={appBarStyle}>
                    {appBar}
                </AppBar>
                <section className={classes.main}>
                    {children}
                </section>
                <footer className={classes.footer}>
                    {footer}
                </footer>
            </section>
        );
    }
}


Layout.propTypes = {
    appBar: PropTypes.element,
    footer: PropTypes.string
};

Layout = withStyles(styles)(Layout);

export {Layout};