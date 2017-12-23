<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8"/>

    <xsl:template match="entries">
    <xsl:text>

    </xsl:text>
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>
    <xsl:template match="entry">
        <xsl:copy>
            <xsl:for-each select="*">
                <xsl:attribute name="{local-name(.)}">
                    <xsl:value-of select="."/>
                </xsl:attribute>
            </xsl:for-each>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>