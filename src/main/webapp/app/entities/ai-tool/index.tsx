import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AiTool from './ai-tool';
import AiToolDetail from './ai-tool-detail';
import AiToolUpdate from './ai-tool-update';
import AiToolDeleteDialog from './ai-tool-delete-dialog';

const AiToolRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AiTool />} />
    <Route path="new" element={<AiToolUpdate />} />
    <Route path=":id">
      <Route index element={<AiToolDetail />} />
      <Route path="edit" element={<AiToolUpdate />} />
      <Route path="delete" element={<AiToolDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AiToolRoutes;
